package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.request.StoryCreateRequest;
import com.comicapp.comic_api.dto.response.StoryResponse;
import com.comicapp.comic_api.entity.Emotion;
import com.comicapp.comic_api.entity.Story;
import com.comicapp.comic_api.repository.EmotionRepository;
import com.comicapp.comic_api.repository.StoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final EmotionRepository emotionRepository;


    @Override
    public List<StoryResponse> getAllStories() {

        List<Story> stories = storyRepository.findAll();
        List<StoryResponse> result = new ArrayList<>();

        for (Story story : stories) {
            result.add(toResponse(story));
        }

        return result;
    }

    @Override
    public List<StoryResponse> getStoriesByEmotion(Long emotionId) {

        List<Story> stories = storyRepository.findByEmotionId(emotionId);
        List<StoryResponse> result = new ArrayList<>();

        for (Story s : stories) {
            result.add(toResponse(s));
        }

        return result;
    }

    @Override
    public StoryResponse getStoryById(Long id) {

        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        return toResponse(story);
    }


    @Override
    public StoryResponse createStory(StoryCreateRequest req) {

        Emotion emotion = emotionRepository.findById(req.getEmotionId())
                .orElseThrow(() -> new RuntimeException("Emotion not found"));

        Story story = new Story();
        story.setTitle(req.getTitle());
        story.setAuthor(req.getAuthor());
        story.setDescription(req.getDescription());
        story.setCoverImage(req.getCoverImage());
        story.setEmotion(emotion);

        Story saved = storyRepository.save(story);
        return toResponse(saved);
    }

    @Override
    public StoryResponse updateStory(Long id, StoryCreateRequest req) {

        Story story = storyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));

        Emotion emotion = emotionRepository.findById(req.getEmotionId())
                .orElseThrow(() -> new RuntimeException("Emotion not found"));

        story.setTitle(req.getTitle());
        story.setAuthor(req.getAuthor());
        story.setDescription(req.getDescription());
        story.setCoverImage(req.getCoverImage());
        story.setEmotion(emotion);

        Story updated = storyRepository.save(story);
        return toResponse(updated);
    }

    @Override
    public void deleteStory(Long id) {
        storyRepository.deleteById(id);
    }

    private StoryResponse toResponse(Story story) {

        StoryResponse res = new StoryResponse();

        res.setId(story.getId());
        res.setTitle(story.getTitle());
        res.setAuthor(story.getAuthor());
        res.setDescription(story.getDescription());
        res.setCoverImage(story.getCoverImage());
        res.setEmotionId(story.getEmotion().getId());
        res.setCreatedAt(story.getCreatedAt());

        return res;
    }
}
