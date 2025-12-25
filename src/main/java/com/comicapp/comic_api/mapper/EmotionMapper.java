package com.comicapp.comic_api.mapper;

import com.comicapp.comic_api.dto.response.EmotionResponse;
import com.comicapp.comic_api.entity.Emotion;
import org.springframework.stereotype.Component;

@Component
public class EmotionMapper {

    public EmotionResponse toResponse(Emotion emotion){
        EmotionResponse emotionResponse = new EmotionResponse();
        emotionResponse.setId(emotion.getId());
        emotionResponse.setName(emotion.getName());
        emotionResponse.setDescription(emotion.getDescription());
        return emotionResponse;
    }
}
