package com.comicapp.comic_api.mapper;

import com.comicapp.comic_api.dto.response.TaskResponse;
import com.comicapp.comic_api.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    // =========================
    // MAP
    // =========================
    public TaskResponse toTaskResponse(Task task) {
        TaskResponse res = new TaskResponse();
        res.setId(task.getId());
        res.setName(task.getName());
        res.setDescription(task.getDescription());
        res.setPointsReward(task.getPointsReward());
        return res;
    }
}
