package com.comicapp.comic_api.service;
import com.comicapp.comic_api.dto.request.StoryCreateRequest;
import com.comicapp.comic_api.dto.response.StoryResponse;

import java.util.List;

public interface StoryService {

    List<StoryResponse> getAllStories();

    List<StoryResponse> getStoriesByEmotion(Long emotionId);

    StoryResponse getStoryById(Long id);

    StoryResponse createStory(StoryCreateRequest request);

    StoryResponse updateStory(Long id, StoryCreateRequest request);

    void deleteStory(Long id);
}