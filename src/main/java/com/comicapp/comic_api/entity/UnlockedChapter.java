package com.comicapp.comic_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "UnlockedChapters",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "chapter_id"})
)
@Data
public class UnlockedChapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    private LocalDateTime unlockedAt;
}

