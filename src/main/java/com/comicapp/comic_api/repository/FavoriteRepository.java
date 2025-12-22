package com.comicapp.comic_api.repository;

import com.comicapp.comic_api.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    // Danh sách yêu thích theo user
    List<Favorite> findByUser_Id(Long userId);

    // Check đã favorite chưa
    boolean existsByUser_IdAndStory_Id(Long userId, Long storyId);

    // Tìm để xoá
    void deleteByUser_IdAndStory_Id(Long userId, Long storyId);

    // (Khuyên dùng) tìm 1 record cụ thể
    Optional<Favorite> findByUser_IdAndStory_Id(Long userId, Long storyId);
}


