package com.comicapp.comic_api.repository;

import com.comicapp.comic_api.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

    List<UserTask> findByUserId(Long userId);

    boolean existsByUserIdAndTaskId(Long userId, Long taskId);
}

