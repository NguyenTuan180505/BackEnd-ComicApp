package com.comicapp.comic_api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
