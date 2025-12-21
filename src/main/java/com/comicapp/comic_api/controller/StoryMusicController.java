package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.dto.request.StoryMusicCreateRequest;
import com.comicapp.comic_api.service.StoryMusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/story-music")
public class StoryMusicController {

    private final StoryMusicService storyMusicService;

    public StoryMusicController(StoryMusicService storyMusicService) {
        this.storyMusicService = storyMusicService;
    }

    // API: POST /story-music
    // Body: { "storyId": 1, "musicId": 2 }
    @PostMapping
    public ResponseEntity<String> addMusicToStory(@RequestBody StoryMusicCreateRequest request) {
        return ResponseEntity.ok(storyMusicService.addMusicToStory(request));
    }

    @GetMapping("/story/{storyId}")
    public ResponseEntity<?> getMusicByStoryId(@PathVariable Long storyId) {
        return ResponseEntity.ok(
                storyMusicService.getMusicByStoryId(storyId)
        );
    }



}