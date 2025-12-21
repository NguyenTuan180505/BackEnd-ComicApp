package com.comicapp.comic_api.repository;

import com.comicapp.comic_api.entity.StoryMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryMusicRepository extends JpaRepository<StoryMusic, Long> {

    List<StoryMusic> findByStoryId(Long storyId);

    boolean existsByStoryIdAndMusicId(Long storyId, Long musicId);

    void deleteByStoryIdAndMusicId(Long storyId, Long musicId);
}
