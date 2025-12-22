package com.comicapp.comic_api.service.impl;

import com.comicapp.comic_api.dto.request.TaskCreateRequest;
import com.comicapp.comic_api.dto.response.TaskResponse;
import com.comicapp.comic_api.entity.Task;
import com.comicapp.comic_api.entity.User;
import com.comicapp.comic_api.entity.UserPoints;
import com.comicapp.comic_api.entity.UserTask;
import com.comicapp.comic_api.mapper.TaskMapper;
import com.comicapp.comic_api.repository.TaskRepository;
import com.comicapp.comic_api.repository.UserPointsRepository;
import com.comicapp.comic_api.repository.UserRepository;
import com.comicapp.comic_api.repository.UserTaskRepository;
import com.comicapp.comic_api.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final UserTaskRepository userTaskRepository;
    private final UserPointsRepository userPointsRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    // =========================
    // LẤY TẤT CẢ TASK
    // =========================
    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toTaskResponse)
                .collect(Collectors.toList());
    }

    // =========================
    // TASK CỦA USER (đã / chưa)
    // =========================
    @Override
    public List<TaskResponse> getMyTasks(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        List<Long> completedTaskIds = userTaskRepository.findByUserId(user.getId())
                .stream()
                .map(userTask -> userTask.getTask().getId())
                .toList();

        return taskRepository.findAll()
                .stream()
                .map(task -> {
                    TaskResponse res = taskMapper.toTaskResponse(task);
                    res.setId(task.getId());
                    res.setCompleted(completedTaskIds.contains(task.getId()));
                    return res;
                })
                .collect(Collectors.toList());
    }


    // =========================
    // HOÀN THÀNH TASK
    // =========================
    @Override
    public void completeTask(String username, Long taskId) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        // 1️⃣ Check task tồn tại
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task không tồn tại"));

        // 2️⃣ Check đã làm chưa
        if (userTaskRepository.existsByUserIdAndTaskId(user.getId(), taskId)) {
            throw new RuntimeException("Bạn đã hoàn thành nhiệm vụ này rồi");
        }


        // 3️⃣ Lưu UserTask
        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(task);
        userTaskRepository.save(userTask);

        // 4️⃣ Cộng điểm
        UserPoints userPoints = userPointsRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    UserPoints up = new UserPoints();
                    up.setUser(user);
                    up.setPoints(up.getPoints()+task.getPointsReward());
                    return up;
                });

        userPoints.setPoints(userPoints.getPoints() + task.getPointsReward());
        userPointsRepository.save(userPoints);
    }

    // =========================
    // ADMIN TẠO TASK
    // =========================
    @Override
    public TaskResponse createTask(TaskCreateRequest request) {
        Task task = new Task();
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setPointsReward(request.getPointsReward());

        return taskMapper.toTaskResponse(taskRepository.save(task));
    }

}
