package com.comicapp.comic_api.controller;
import com.comicapp.comic_api.dto.request.ChapterCreateRequest;
import com.comicapp.comic_api.dto.response.ChapterDetailResponse;
import com.comicapp.comic_api.dto.response.ChapterResponse;
import com.comicapp.comic_api.service.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/chapters")
@RequiredArgsConstructor
public class ChapterController {
    private final ChapterService chapterService;

    // Lấy tất cả chương hoặc lọc theo storyId
    @GetMapping
    public ResponseEntity<List<ChapterResponse>> getAllChapters(
            @RequestParam(required = false) Long storyId) {
        if (storyId != null) {
            return ResponseEntity.ok(chapterService.getChaptersByStoryId(storyId));
        }
        return ResponseEntity.ok(chapterService.getAllChapters());
    }
    // Lấy chi tiết chương theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ChapterDetailResponse> getChapterById(@PathVariable Long id) {
        return ResponseEntity.ok(chapterService.getChapterById(id));
    }
    // Tạo mới chương
    @PostMapping
    public ResponseEntity<ChapterResponse> createChapter(
            @Valid @RequestBody ChapterCreateRequest request) {
        return ResponseEntity.ok(chapterService.createChapter(request));
    }

    // Cập nhật chương
    @PutMapping("/{id}")
    public ResponseEntity<ChapterResponse> updateChapter(
            @PathVariable Long id,
            @Valid @RequestBody ChapterCreateRequest request) {
        return ResponseEntity.ok(chapterService.updateChapter(id, request));
    }

    // Xóa chương
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        chapterService.deleteChapter(id);
        return ResponseEntity.noContent().build();
    }
}
