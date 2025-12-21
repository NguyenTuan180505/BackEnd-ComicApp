package com.comicapp.comic_api.dto.response;

import lombok.Data;

@Data
public class StoryMusicResponse {
    private Long id;
    private Long storyId;
    private Long musicId;
}

