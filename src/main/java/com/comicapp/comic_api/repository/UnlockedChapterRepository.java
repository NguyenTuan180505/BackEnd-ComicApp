package com.comicapp.comic_api.repository;

import com.comicapp.comic_api.entity.UnlockedChapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnlockedChapterRepository extends JpaRepository<UnlockedChapter, Long> {

    boolean existsByUserIdAndChapterId(Long userId, Long chapterId);
}

