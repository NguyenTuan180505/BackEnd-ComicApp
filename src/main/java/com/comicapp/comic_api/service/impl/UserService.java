package com.comicapp.comic_api.service.impl;

import com.comicapp.comic_api.dto.request.UserCreateRequest;
import com.comicapp.comic_api.dto.response.UserPointsResponse;
import com.comicapp.comic_api.dto.response.UserResponse;
import com.comicapp.comic_api.entity.User;
import com.comicapp.comic_api.entity.UserPoints;
import com.comicapp.comic_api.mapper.UserMapper;
import com.comicapp.comic_api.mapper.UserPointsMapper;
import com.comicapp.comic_api.repository.UserPointsRepository;
import com.comicapp.comic_api.repository.UserRepository;
import com.comicapp.comic_api.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserPointsRepository userPointsRepository;
    private final UserPointsMapper userPointsMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        return null;
    }

    @Override
    public UserResponse updateUser(Long id, UserCreateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));


        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User không tồn tại");
        }
        userRepository.deleteById(id);
    }


    @Override
    public UserResponse getCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserPointsResponse getUserPoints(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        UserPoints userPoints = userPointsRepository.findByUserId(user.getId())
                .orElseThrow(()-> new RuntimeException("User không tồn tại "));

        return userPointsMapper.toResponse(userPoints);
    }
}
