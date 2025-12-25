package com.comicapp.comic_api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteResponse {
    private Long id;
    private Long userId;
    private Long storyId;
    private LocalDateTime createdAt;
}

