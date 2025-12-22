package com.comicapp.comic_api.mapper;

import com.comicapp.comic_api.dto.request.UserCreateRequest;
import com.comicapp.comic_api.dto.response.UserResponse;
import com.comicapp.comic_api.entity.Role;
import com.comicapp.comic_api.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
