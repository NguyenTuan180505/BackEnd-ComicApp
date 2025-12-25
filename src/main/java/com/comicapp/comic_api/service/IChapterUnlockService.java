package com.comicapp.comic_api.service;

public interface IChapterUnlockService {
    void unlockChapter(String username, Long chapterId);
    boolean isUnlocked(String username, Long chapterId);

}
