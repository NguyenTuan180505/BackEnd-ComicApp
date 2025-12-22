package com.comicapp.comic_api.service.impl;

import com.comicapp.comic_api.dto.request.UserCreateRequest;
import com.comicapp.comic_api.dto.response.LoginResponse;
import com.comicapp.comic_api.dto.response.UserResponse;
import com.comicapp.comic_api.entity.User;
import com.comicapp.comic_api.mapper.UserMapper;
import com.comicapp.comic_api.repository.UserRepository;
import com.comicapp.comic_api.service.IAuthService;
import com.comicapp.comic_api.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("tên đăng nhập, mật khẩu không đúng hoặc không tồn tại"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("tên đăng nhập hoặc mật khẩu không đúng");
        }
        String token = jwtUtils.generateToken(user.getUsername(),user.getRole().toString());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return response;
    }
    @Override
    public UserResponse register(UserCreateRequest request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username đã tồn tại");
        }
        if (request.getEmail() != null && userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return userMapper.toResponse(user);
    }
}

