package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.repository.UserRepository;
import com.comicapp.comic_api.service.IChapterUnlockService;
import com.comicapp.comic_api.service.impl.ChapterUnlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/unlock-chapters")
@RequiredArgsConstructor
public class ChapterUnlockController {

    private final IChapterUnlockService unlockService;
    private final UserRepository userRepository;

    // 👉 ĐỔI ĐIỂM -> MỞ KHÓA
    @PostMapping("/{chapterId}/unlock")
    public ResponseEntity<?> unlock(
            @PathVariable Long chapterId,
            @AuthenticationPrincipal String username
    ) {

        unlockService.unlockChapter(username, chapterId);

        return ResponseEntity.ok("Mở khóa thành công");
    }

    // 👉 CHECK ĐÃ MỞ KHÓA CHƯA
    @GetMapping("/{chapterId}/is-unlocked")
    public ResponseEntity<Boolean> isUnlocked(
            @PathVariable Long chapterId,
            @AuthenticationPrincipal String username
    ) {

        boolean result = unlockService.isUnlocked(username, chapterId);

        return ResponseEntity.ok(result);
    }
}
