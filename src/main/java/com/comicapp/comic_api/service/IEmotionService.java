package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.response.EmotionResponse;
import com.comicapp.comic_api.dto.response.StoryResponse;

import java.util.List;

public interface IEmotionService {

    List<EmotionResponse> getAllEmotions();
    List<StoryResponse> getStoriesByEmotion(Long emotionId);
}
