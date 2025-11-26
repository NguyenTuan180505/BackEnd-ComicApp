package com.comicapp.comic_api.repository;

import com.comicapp.comic_api.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findByEmotionId(Long emotionId);
}

