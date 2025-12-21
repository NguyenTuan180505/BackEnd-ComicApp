package com.comicapp.comic_api.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.Data;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    // --- PHẦN QUAN TRỌNG BẮT BUỘC PHẢI CÓ ---
    // (Nếu thiếu đoạn này, App sẽ không khởi động được vì lỗi mapping bên Emotion)
    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;
    // ----------------------------------------

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "story" , cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<StoryMusic> storyMusic;

    // Các list khác (Chapter, Comment...) nếu bên kia chưa viết xong
    // thì bạn có thể tạm comment lại, nhưng cái Emotion ở trên thì BẮT BUỘC phải mở.
}