package com.comicapp.comic_api.dto.request;

import lombok.Data;

@Data
public class TaskCreateRequest {
    private String name;
    private String description;
    private Integer pointsReward;
}

