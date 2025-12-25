package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.dto.request.CommentCreateRequest;
import com.comicapp.comic_api.dto.response.CommentResponse;
import com.comicapp.comic_api.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentService commentService;

    // GET /comments?storyId=1
    @GetMapping
    public List<CommentResponse> getComments(
            @RequestParam Long storyId) {
        return commentService.getCommentsByStory(storyId);
    }

    // POST /comments
    @PostMapping
    public CommentResponse createComment(
            @AuthenticationPrincipal String username,
            @RequestBody CommentCreateRequest request) {
        return commentService.createComment(request, username);
    }

    // DELETE /comments/{id}
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
