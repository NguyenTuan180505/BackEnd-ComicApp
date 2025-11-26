package com.comicapp.comic_api.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Favorites")
@Data
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}

