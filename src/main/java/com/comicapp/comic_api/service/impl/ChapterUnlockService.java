package com.comicapp.comic_api.service.impl;

import com.comicapp.comic_api.entity.Chapter;
import com.comicapp.comic_api.entity.UnlockedChapter;
import com.comicapp.comic_api.entity.User;
import com.comicapp.comic_api.entity.UserPoints;
import com.comicapp.comic_api.repository.ChapterRepository;
import com.comicapp.comic_api.repository.UnlockedChapterRepository;
import com.comicapp.comic_api.repository.UserPointsRepository;
import com.comicapp.comic_api.repository.UserRepository;
import com.comicapp.comic_api.service.IChapterUnlockService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChapterUnlockService implements IChapterUnlockService{

    private final UserPointsRepository userPointsRepo;
    private final ChapterRepository chapterRepo;
    private final UnlockedChapterRepository unlockedRepo;
    private final UserRepository userRepository;

    private static final int COST = 20; // chi phí mở khóa

    @Override
    @Transactional
    public void unlockChapter(String username, Long chapterId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        Chapter chapter = chapterRepo.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chương"));

        // nếu chương ko khóa thì khỏi trừ điểm
        if (!Boolean.TRUE.equals(chapter.getIsLocked())) {
            return;
        }

        // nếu đã mở rồi thì không trừ điểm nữa
        if (unlockedRepo.existsByUserIdAndChapterId(user.getId(), chapterId)) {
            return;
        }

        UserPoints points = userPointsRepo.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("User chưa có điểm"));

        if (points.getPoints() < COST) {
            throw new RuntimeException("Không đủ điểm để mở khóa");
        }

        // trừ điểm
        points.setPoints(points.getPoints() - COST);
        userPointsRepo.save(points);

        // lưu mở khóa
        UnlockedChapter unlocked = new UnlockedChapter();
        unlocked.setUser(user);
        unlocked.setChapter(chapter);
        unlocked.setUnlockedAt(LocalDateTime.now());

        unlockedRepo.save(unlocked);
    }

    @Override
    public boolean isUnlocked(String username, Long chapterId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        Chapter chapter = chapterRepo.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chương"));

        if (!Boolean.TRUE.equals(chapter.getIsLocked())) {
            return true; // chương free
        }

        return unlockedRepo.existsByUserIdAndChapterId(user.getId(), chapterId);
    }
}

