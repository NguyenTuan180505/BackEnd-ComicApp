package com.comicapp.comic_api.dto.request;

import lombok.Data;

@Data
public class EmotionCreateRequest {
    private String name;
    private String description;
}

