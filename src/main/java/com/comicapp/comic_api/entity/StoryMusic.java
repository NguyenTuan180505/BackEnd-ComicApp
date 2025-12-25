package com.comicapp.comic_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "StoryMusic")
@Data
public class StoryMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;
}

