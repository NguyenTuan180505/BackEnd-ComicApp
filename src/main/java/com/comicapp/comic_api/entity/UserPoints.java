package com.comicapp.comic_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "UserPoints")
@Data
public class UserPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private Integer points = 0;
}

