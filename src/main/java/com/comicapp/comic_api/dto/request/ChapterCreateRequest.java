package com.comicapp.comic_api.dto.request;

import lombok.Data;

@Data
public class ChapterCreateRequest {
    private Long storyId;
    private String title;
    private String content;
    private Integer chapterNumber;
}

