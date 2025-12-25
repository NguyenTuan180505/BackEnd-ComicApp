package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.dto.request.MusicCreateRequest;
import com.comicapp.comic_api.dto.response.MusicResponse;
import com.comicapp.comic_api.service.impl.MusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    // GET /music
    @GetMapping
    public ResponseEntity<List<MusicResponse>> getAllMusic() {
        return ResponseEntity.ok(musicService.getAllMusic());
    }

    // POST /music
    @PostMapping
    public ResponseEntity<MusicResponse> createMusic(
            @RequestBody MusicCreateRequest request
    ) {
        return ResponseEntity.ok(musicService.createMusic(request));
    }

    // DELETE /music/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMusic(@PathVariable Long id) {
        musicService.deleteMusic(id);
        return ResponseEntity.ok("Delete music success");
    }
    // PUT /music/{id}
    // API Sửa thông tin bài hát
    @PutMapping("/{id}")
    public ResponseEntity<MusicResponse> updateMusic(
            @PathVariable Long id,
            @RequestBody MusicCreateRequest request
    ) {
        return ResponseEntity.ok(musicService.updateMusic(id, request));
    }
}
