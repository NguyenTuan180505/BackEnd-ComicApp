package com.comicapp.comic_api.dto.response;

import lombok.Data;

@Data
public class TaskResponse {
    private Long id;
    private String name;
    private String description;
    private Integer pointsReward;
    private boolean completed;
}
