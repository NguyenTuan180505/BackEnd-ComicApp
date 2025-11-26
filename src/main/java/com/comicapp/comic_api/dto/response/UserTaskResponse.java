package com.comicapp.comic_api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTaskResponse {
    private Long id;
    private Long userId;
    private Long taskId;
    private LocalDateTime completedAt;
}

