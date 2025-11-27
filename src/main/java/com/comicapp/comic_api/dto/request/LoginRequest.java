package com.comicapp.comic_api.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginRequest {
    private String username;
    private String password;
}

