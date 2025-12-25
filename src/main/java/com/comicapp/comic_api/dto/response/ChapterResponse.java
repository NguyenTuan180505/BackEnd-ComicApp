package com.comicapp.comic_api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChapterResponse {
    private Long id;
    private Long storyId;
    private String title;
    private Integer chapterNumber;
    private Boolean isLocked;
    private LocalDateTime createdAt;
}

