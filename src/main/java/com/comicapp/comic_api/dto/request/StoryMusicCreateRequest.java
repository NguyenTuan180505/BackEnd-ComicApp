package com.comicapp.comic_api.dto.request;

import lombok.Data;

@Data
public class StoryMusicCreateRequest {
    private Long storyId;
    private Long musicId;
}

