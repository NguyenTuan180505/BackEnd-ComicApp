package com.comicapp.comic_api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MusicResponse {
    private Long id;
    private String name;
    private String url;
    private LocalDateTime createdAt;
}

