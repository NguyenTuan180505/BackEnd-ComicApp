package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.request.CommentCreateRequest;
import com.comicapp.comic_api.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {

    List<CommentResponse> getCommentsByStory(Long storyId);

    CommentResponse createComment(CommentCreateRequest request);

    void deleteComment(Long commentId);
}
