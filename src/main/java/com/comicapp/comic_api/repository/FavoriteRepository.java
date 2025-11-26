package com.comicapp.comic_api.repository;

import com.comicapp.comic_api.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUserId(Long userId);

    boolean existsByUserIdAndStoryId(Long userId, Long storyId);

    void deleteByUserIdAndStoryId(Long userId, Long storyId);
}

