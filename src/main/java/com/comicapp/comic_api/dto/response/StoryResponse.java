package com.comicapp.comic_api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoryResponse {
    private Long id;
    private String title;
    private String author;
    private String description;
    private Long emotionId;
    private String coverImage;
    private LocalDateTime createdAt;
}

