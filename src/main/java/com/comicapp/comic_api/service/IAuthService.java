package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.request.UserCreateRequest;
import com.comicapp.comic_api.dto.response.LoginResponse;
import com.comicapp.comic_api.dto.response.UserResponse;

public interface IAuthService {
    LoginResponse login(String username, String password);
    UserResponse register(UserCreateRequest request);
}
