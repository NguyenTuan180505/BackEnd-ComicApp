package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.dto.response.EmotionResponse;
import com.comicapp.comic_api.dto.response.StoryResponse;
import com.comicapp.comic_api.service.impl.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emotions")
@RequiredArgsConstructor
public class EmotionController {

    private final EmotionService emotionService;

    @GetMapping
    public ResponseEntity<List<EmotionResponse>> getAllEmotions(){
        return ResponseEntity.ok(emotionService.getAllEmotions());
    }

    @GetMapping("/{emotionId}/stories")
    public ResponseEntity<List<StoryResponse>> getStoryByEmotion(@PathVariable Long emotionId){
        return  ResponseEntity.ok(emotionService.getStoriesByEmotion(emotionId));
    }
}
