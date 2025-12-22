package com.comicapp.comic_api.service.impl;

import com.comicapp.comic_api.dto.response.EmotionResponse;
import com.comicapp.comic_api.dto.response.StoryResponse;
import com.comicapp.comic_api.entity.Emotion;
import com.comicapp.comic_api.entity.Story;
import com.comicapp.comic_api.mapper.EmotionMapper;
import com.comicapp.comic_api.mapper.StoryMapper;
import com.comicapp.comic_api.repository.EmotionRepository;
import com.comicapp.comic_api.repository.StoryRepository;
import com.comicapp.comic_api.service.IEmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmotionService implements IEmotionService {

    private final EmotionRepository emotionRepository;
    private final EmotionMapper emotionMapper;
    private final StoryRepository storyRepository;
    private final StoryMapper storyMapper;

    @Override
    public List<EmotionResponse> getAllEmotions() {
        return emotionRepository.findAll()
                .stream()
                .map(emotionMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StoryResponse> getStoriesByEmotion(Long emotionId) {
        List<Story> stories = storyRepository.findByEmotionId(emotionId);
        return stories.stream().map(storyMapper::toResponse).toList();
    }
}
