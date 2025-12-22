package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.dto.request.FavoriteCreateRequest;
import com.comicapp.comic_api.dto.response.FavoriteResponse;
import com.comicapp.comic_api.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // 1️⃣ Thêm truyện yêu thích
    @PostMapping
    public ResponseEntity<FavoriteResponse> addFavorite(
            @RequestBody FavoriteCreateRequest request) {
        return ResponseEntity.ok(favoriteService.addFavorite(request));
    }

    // 2️⃣ Lấy danh sách yêu thích theo user
    @GetMapping("me")
    public ResponseEntity<List<FavoriteResponse>> getFavoritesByUser(
            @AuthenticationPrincipal String usename) {
        return ResponseEntity.ok(favoriteService.getFavoritesByUser(usename));
    }

    // 3️⃣ Xoá truyện yêu thích
    @DeleteMapping
    public ResponseEntity<Void> removeFavorite(
            @AuthenticationPrincipal String usename,
            @RequestParam Long storyId) {
        favoriteService.removeFavorite(usename, storyId);
        return ResponseEntity.noContent().build();
    }
}
