package com.comicapp.comic_api.dto.request;

import lombok.Data;

@Data
public class UserTaskCreateRequest {
    private Long userId;
    private Long taskId;
}

