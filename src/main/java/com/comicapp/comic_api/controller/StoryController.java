package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.dto.request.StoryCreateRequest;
import com.comicapp.comic_api.dto.response.StoryResponse;
import com.comicapp.comic_api.dto.response.ChapterResponse;
import com.comicapp.comic_api.entity.Chapter;
import com.comicapp.comic_api.repository.ChapterRepository;
import com.comicapp.comic_api.service.StoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stories")
@RequiredArgsConstructor
public class StoryController {

    private final StoryService storyService;

    @GetMapping
    public List<StoryResponse> getAllStories() {
        return storyService.getAllStories();
    }

    @GetMapping("/emotion/{emotionId}")
    public List<StoryResponse> getStoriesByEmotion(@PathVariable Long emotionId) {
        return storyService.getStoriesByEmotion(emotionId);
    }

    @GetMapping("/{id}")
    public StoryResponse getStoryDetail(@PathVariable Long id) {
        return storyService.getStoryById(id);
    }


    @PostMapping
    public StoryResponse createStory(@RequestBody StoryCreateRequest req) {
        return storyService.createStory(req);
    }

    @PutMapping("/{id}")
    public StoryResponse updateStory(@PathVariable Long id, @RequestBody StoryCreateRequest req) {
        return storyService.updateStory(id, req);
    }

    @DeleteMapping("/{id}")
    public String deleteStory(@PathVariable Long id) {
        storyService.deleteStory(id);
        return "Story deleted successfully";
    }
}
