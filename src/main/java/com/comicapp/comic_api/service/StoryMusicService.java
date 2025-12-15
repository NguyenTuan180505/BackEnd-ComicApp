package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.request.StoryMusicCreateRequest;
import com.comicapp.comic_api.entity.Music;
import com.comicapp.comic_api.entity.Story;
import com.comicapp.comic_api.entity.StoryMusic;
import com.comicapp.comic_api.repository.MusicRepository;
import com.comicapp.comic_api.repository.StoryMusicRepository;
import com.comicapp.comic_api.repository.StoryRepository;
import org.springframework.stereotype.Service;

@Service
public class StoryMusicService {

    private final StoryMusicRepository storyMusicRepository;
    private final StoryRepository storyRepository;
    private final MusicRepository musicRepository;

    public StoryMusicService(StoryMusicRepository storyMusicRepository,
                             StoryRepository storyRepository,
                             MusicRepository musicRepository) {
        this.storyMusicRepository = storyMusicRepository;
        this.storyRepository = storyRepository;
        this.musicRepository = musicRepository;
    }

    // Chức năng: Thêm nhạc vào truyện
    public String addMusicToStory(StoryMusicCreateRequest request) {
        // 1. Kiểm tra xem Truyện có tồn tại không
        Story story = storyRepository.findById(request.getStoryId())
                .orElseThrow(() -> new RuntimeException("Story not found"));

        // 2. Kiểm tra xem Nhạc có tồn tại không
        Music music = musicRepository.findById(request.getMusicId())
                .orElseThrow(() -> new RuntimeException("Music not found"));

        // 3. Kiểm tra xem đã gán chưa (tránh trùng lặp)
        if (storyMusicRepository.existsByStoryIdAndMusicId(story.getId(), music.getId())) {
            return "Music already added to this story!";
        }

        // 4. Lưu vào bảng trung gian
        StoryMusic storyMusic = new StoryMusic();
        storyMusic.setStory(story);
        storyMusic.setMusic(music);

        storyMusicRepository.save(storyMusic);

        return "Add music to story success!";
    }
}