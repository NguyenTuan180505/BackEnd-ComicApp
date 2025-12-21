package com.comicapp.comic_api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private Long id;
    private Long storyId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
}
