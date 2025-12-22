package com.comicapp.comic_api.service.impl;

import com.comicapp.comic_api.dto.request.CommentCreateRequest;
import com.comicapp.comic_api.dto.response.CommentResponse;
import com.comicapp.comic_api.entity.Comment;
import com.comicapp.comic_api.entity.Story;
import com.comicapp.comic_api.entity.User;
import com.comicapp.comic_api.repository.CommentRepository;
import com.comicapp.comic_api.repository.StoryRepository;
import com.comicapp.comic_api.repository.UserRepository;
import com.comicapp.comic_api.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    private final StoryRepository storyRepository;
    private final UserRepository userRepository;

    @Override
    public List<CommentResponse> getCommentsByStory(Long storyId) {
        return commentRepository
                .findByStory_IdOrderByCreatedAtDesc(storyId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse createComment(CommentCreateRequest request) {

        Story story = storyRepository.findById(request.getStoryId())
                .orElseThrow(() -> new RuntimeException("Story not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setStory(story);
        comment.setUser(user);
        comment.setContent(request.getContent());

        comment = commentRepository.save(comment);

        return mapToResponse(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    private CommentResponse mapToResponse(Comment comment) {
        CommentResponse res = new CommentResponse();
        res.setId(comment.getId());
        res.setStoryId(comment.getStory().getId());
        res.setUserId(comment.getUser().getId());
        res.setContent(comment.getContent());
        res.setCreatedAt(comment.getCreatedAt());
        return res;
    }
}
