package com.comicapp.comic_api.controller;

import com.comicapp.comic_api.dto.request.UserCreateRequest;
import com.comicapp.comic_api.dto.response.UserPointsResponse;
import com.comicapp.comic_api.dto.response.UserResponse;
import com.comicapp.comic_api.entity.User;
import com.comicapp.comic_api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    // ==========================
    // GET ALL
    // ==========================
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ==========================
    // GET BY ID
    // ==========================
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
//
//    // ==========================
//    // CREATE
//    // ==========================
//    @PostMapping
//    public ResponseEntity<UserResponse> createUser(
//            @RequestBody UserCreateRequest request) {
//        return ResponseEntity.ok(userService.createUser(request));
//    }

    // ==========================
    // UPDATE
    // ==========================
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserCreateRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    // ==========================
    // DELETE
    // ==========================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // ==========================
    // GET CURRENT USER
    // ==========================
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(
            @AuthenticationPrincipal String username) {

        return ResponseEntity.ok(
                userService.getCurrentUser(username)
        );
    }

    @GetMapping("/me/points")
    public ResponseEntity<UserPointsResponse> getUserPoints(@AuthenticationPrincipal String username){
        return ResponseEntity.ok(userService.getUserPoints(username));
    }


}
