package com.comicapp.comic_api.service.impl;

import com.comicapp.comic_api.dto.request.MusicCreateRequest;
import com.comicapp.comic_api.dto.response.MusicResponse;
import com.comicapp.comic_api.entity.Music;
import com.comicapp.comic_api.repository.MusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {

    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    // Lấy danh sách nhạc
    public List<MusicResponse> getAllMusic() {
        return musicRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Tạo nhạc mới
    public MusicResponse createMusic(MusicCreateRequest request) {
        Music music = new Music();
        music.setName(request.getName());
        music.setUrl(request.getUrl());

        Music savedMusic = musicRepository.save(music);
        return toResponse(savedMusic);
    }

    // Xoá nhạc
    public void deleteMusic(Long id) {
        musicRepository.deleteById(id);
    }

    // Map Entity → Response
    private MusicResponse toResponse(Music music) {
        MusicResponse response = new MusicResponse();
        response.setId(music.getId());
        response.setName(music.getName());
        response.setUrl(music.getUrl());
        response.setCreatedAt(music.getCreatedAt());
        return response;
    }

    // --- BỔ SUNG: Sửa thông tin nhạc ---
    public MusicResponse updateMusic(Long id, MusicCreateRequest request) {
        // 1. Tìm bài hát cũ xem có tồn tại không
        Music existingMusic = musicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Music not found with id: " + id));

        // 2. Cập nhật thông tin mới
        existingMusic.setName(request.getName());
        existingMusic.setUrl(request.getUrl());

        // 3. Lưu lại (Lúc này JPA tự hiểu là Update vì object đã có ID)
        Music updatedMusic = musicRepository.save(existingMusic);

        return toResponse(updatedMusic);
    }
}
