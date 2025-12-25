package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.request.TaskCreateRequest;
import com.comicapp.comic_api.dto.response.TaskResponse;

import java.util.List;

public interface ITaskService {
    List<TaskResponse> getAllTasks();

    List<TaskResponse> getMyTasks(String username);

    void completeTask(String username, Long taskId);

    TaskResponse createTask(TaskCreateRequest request);
    TaskResponse updateTask(Long taskId, TaskCreateRequest request);
}
