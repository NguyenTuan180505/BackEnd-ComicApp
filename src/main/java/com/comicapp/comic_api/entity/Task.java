package com.comicapp.comic_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "points_reward")
    private Integer pointsReward = 10;

    @OneToMany(mappedBy = "task")
    private List<UserTask> userTasks;
}
