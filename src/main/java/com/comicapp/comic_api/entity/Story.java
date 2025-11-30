package com.comicapp.comic_api.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Stories")
@Data
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String description;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @OneToMany(mappedBy = "story")
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "story")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "story")
    private List<Comment> comments;

    @OneToMany(mappedBy = "story")
    private List<StoryMusic> storyMusic;
}

