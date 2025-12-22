package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.request.ChapterCreateRequest;
import com.comicapp.comic_api.dto.response.ChapterDetailResponse;
import com.comicapp.comic_api.dto.response.ChapterResponse;
import com.comicapp.comic_api.entity.Chapter;
import com.comicapp.comic_api.entity.Story;
import com.comicapp.comic_api.mapper.ChapterMapper;
import com.comicapp.comic_api.repository.ChapterRepository;
import com.comicapp.comic_api.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final StoryRepository storyRepository;
    private final ChapterMapper chapterMapper;

    @Override
    public List<ChapterResponse> getAllChapters() {
        return chapterRepository.findAll().stream()
                .map(chapterMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChapterResponse> getChaptersByStoryId(Long storyId) {
        return chapterRepository.findByStoryId(storyId).stream()
                .map(chapterMapper::toResponse)
                .collect(Collectors.toList());
    }
//    public ChapterDetailResponse getChapterDetail(Long id) {
//        Chapter chapter = chapterRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(
//                        HttpStatus.NOT_FOUND,
//                        "Không tìm thấy chương"
//                ));
//        return chapterMapper.toDetailResponse(chapter);
//    }

    @Override
    public ChapterDetailResponse getChapterById(Long id) {
        Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy chương với ID: " + id
                ));
        return chapterMapper.toDetailResponse(chapter);
    }

    @Override
    public ChapterResponse createChapter(ChapterCreateRequest request) {
        Story story = storyRepository.findById(request.getStoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy truyện với ID: " + request.getStoryId()
                ));

        Chapter chapter = chapterMapper.toEntity(request);
        chapter.setStory(story);
        Chapter savedChapter = chapterRepository.save(chapter);
        return chapterMapper.toResponse(savedChapter);
    }

    @Override
    public ChapterResponse updateChapter(Long id, ChapterCreateRequest request) {
        Chapter existingChapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy chương với ID: " + id
                ));

        Story story = storyRepository.findById(request.getStoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Không tìm thấy truyện với ID: " + request.getStoryId()
                ));

        existingChapter.setTitle(request.getTitle());
        existingChapter.setContent(request.getContent());
        existingChapter.setChapterNumber(request.getChapterNumber());
        existingChapter.setIsLocked(request.getIsLocked());
        existingChapter.setStory(story);

        Chapter updatedChapter = chapterRepository.save(existingChapter);
        return chapterMapper.toResponse(updatedChapter);
    }

    @Override
    public void deleteChapter(Long id) {
        if (!chapterRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Không tìm thấy chương với ID: " + id
            );
        }
        chapterRepository.deleteById(id);
    }
}
