package com.comicapp.comic_api.repository;

import com.comicapp.comic_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByStory_IdOrderByCreatedAtDesc(Long storyId);

}

