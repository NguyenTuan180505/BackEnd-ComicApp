package com.comicapp.comic_api.dto.request;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private Long storyId;
    private String content;
}

