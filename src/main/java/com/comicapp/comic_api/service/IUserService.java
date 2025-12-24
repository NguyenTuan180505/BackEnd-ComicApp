package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.request.UserChangePasswordRequest;
import com.comicapp.comic_api.dto.request.UserCreateRequest;
import com.comicapp.comic_api.dto.response.UserPointsResponse;
import com.comicapp.comic_api.dto.response.UserResponse;
import com.comicapp.comic_api.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse createUser(UserCreateRequest request);

    UserResponse updateUser(Long id, UserCreateRequest request);

    void deleteUser(Long id);

    UserResponse getCurrentUser(String username);

    UserPointsResponse getUserPoints(String user);
    UserResponse changePassword(String username, UserChangePasswordRequest request);


}
