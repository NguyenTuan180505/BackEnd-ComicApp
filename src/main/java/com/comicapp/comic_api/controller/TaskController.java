package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.dto.request.TaskCreateRequest;
import com.comicapp.comic_api.dto.response.TaskResponse;
import com.comicapp.comic_api.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final ITaskService taskService;

    // =========================
    // LẤY TẤT CẢ TASK
    // =========================
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    // =========================
    // TASK CỦA USER
    // =========================
    @GetMapping("/my")
    public List<TaskResponse> getMyTasks(@AuthenticationPrincipal String username) {
        return taskService.getMyTasks(username);
    }

    // =========================
    // HOÀN THÀNH TASK
    // =========================
    @PostMapping("/{taskId}/complete")
    public String completeTask(@PathVariable Long taskId, @AuthenticationPrincipal String username) {
        taskService.completeTask(username, taskId);
        return "Hoàn thành nhiệm vụ thành công";
    }

// ADMIN SỬA TASK
// =========================
    @PutMapping("/{taskId}")
    public TaskResponse updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskCreateRequest request
    ) {
        return taskService.updateTask(taskId, request);
    }

    // =========================
    // ADMIN TẠO TASK
    // =========================
    @PostMapping
    public TaskResponse createTask(@RequestBody TaskCreateRequest request) {
        return taskService.createTask(request);
    }

}
