package com.comicapp.comic_api.dto.request;

import lombok.Data;

@Data
public class StoryCreateRequest {
    private String title;
    private String author;
    private String description;
    private Long emotionId;
    private String coverImage;
}

