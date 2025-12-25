package com.comicapp.comic_api.dto.response;

import lombok.Data;

@Data
public class UserPointsResponse {
    private Long id;
    private Long userId;
    private Integer points;
}

