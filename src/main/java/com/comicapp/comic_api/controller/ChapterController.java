package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.dto.request.ChapterCreateRequest;
import com.comicapp.comic_api.dto.response.ChapterResponse;
import com.comicapp.comic_api.entity.Chapter;
import com.comicapp.comic_api.entity.Story;
import com.comicapp.comic_api.repository.ChapterRepository;
import com.comicapp.comic_api.repository.StoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    private final ChapterRepository chapterRepository;
    private final StoryRepository storyRepository;

    public ChapterController(ChapterRepository chapterRepository, StoryRepository storyRepository) {
        this.chapterRepository = chapterRepository;
        this.storyRepository = storyRepository;
    }

    @GetMapping
    public List<ChapterResponse> getChapters(@RequestParam(value = "storyId", required = false) Long storyId) {
        List<Chapter> chapters = storyId != null ? chapterRepository.findByStoryId(storyId) : chapterRepository.findAll();
        return chapters.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ChapterResponse getChapter(@PathVariable Long id) {
        Chapter chapter = chapterRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return toResponse(chapter);
    }

    @PostMapping
    public ResponseEntity<ChapterResponse> createChapter(@RequestBody ChapterCreateRequest request) {
        if (request.getStoryId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Story story = storyRepository.findById(request.getStoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Chapter chapter = new Chapter();
        chapter.setStory(story);
        chapter.setTitle(request.getTitle());
        chapter.setContent(request.getContent());
        chapter.setChapterNumber(request.getChapterNumber());
        Chapter saved = chapterRepository.save(chapter);
        return new ResponseEntity<>(toResponse(saved), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ChapterResponse updateChapter(@PathVariable Long id, @RequestBody ChapterCreateRequest request) {
        Chapter chapter = chapterRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (request.getStoryId() != null) {
            Story story = storyRepository.findById(request.getStoryId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            chapter.setStory(story);
        }
        Optional.ofNullable(request.getTitle()).ifPresent(chapter::setTitle);
        Optional.ofNullable(request.getContent()).ifPresent(chapter::setContent);
        Optional.ofNullable(request.getChapterNumber()).ifPresent(chapter::setChapterNumber);
        Chapter saved = chapterRepository.save(chapter);
        return toResponse(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        if (!chapterRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        chapterRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ChapterResponse toResponse(Chapter chapter) {
        ChapterResponse response = new ChapterResponse();
        response.setId(chapter.getId());
        response.setStoryId(chapter.getStory() != null ? chapter.getStory().getId() : null);
        response.setTitle(chapter.getTitle());
        response.setContent(chapter.getContent());
        response.setChapterNumber(chapter.getChapterNumber());
        response.setIsLocked(chapter.getIsLocked());
        response.setCreatedAt(chapter.getCreatedAt());
        return response;
    }
}

