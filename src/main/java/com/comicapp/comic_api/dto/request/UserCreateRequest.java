package com.comicapp.comic_api.dto.request;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String password;
    private String email;
}

