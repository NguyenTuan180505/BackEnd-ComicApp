package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.request.ChapterCreateRequest;
import com.comicapp.comic_api.dto.response.ChapterDetailResponse;
import com.comicapp.comic_api.dto.response.ChapterResponse;

import java.util.List;

public interface ChapterService {
    List<ChapterResponse> getAllChapters();
//    ChapterDetailResponse getChapterDetail(Long id);
    List<ChapterResponse> getChaptersByStoryId(Long storyId);
    ChapterDetailResponse getChapterById(Long id);
    ChapterResponse createChapter(ChapterCreateRequest request);
    ChapterResponse updateChapter(Long id, ChapterCreateRequest request);
    void deleteChapter(Long id);
}