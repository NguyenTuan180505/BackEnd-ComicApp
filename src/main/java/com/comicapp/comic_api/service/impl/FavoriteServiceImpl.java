package com.comicapp.comic_api.service.impl;


import com.comicapp.comic_api.dto.request.FavoriteCreateRequest;
import com.comicapp.comic_api.dto.response.FavoriteResponse;
import com.comicapp.comic_api.entity.Favorite;
import com.comicapp.comic_api.entity.Story;
import com.comicapp.comic_api.entity.User;
import com.comicapp.comic_api.repository.FavoriteRepository;
import com.comicapp.comic_api.repository.StoryRepository;
import com.comicapp.comic_api.repository.UserRepository;
import com.comicapp.comic_api.service.FavoriteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final StoryRepository storyRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository,
                               UserRepository userRepository,
                               StoryRepository storyRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.storyRepository = storyRepository;
    }

    @Override
    public FavoriteResponse addFavorite(FavoriteCreateRequest request) {

        // ✅ Check đã tồn tại
        if (favoriteRepository.existsByUser_IdAndStory_Id(
                request.getUserId(), request.getStoryId())) {
            throw new RuntimeException("Story already in favorites");
        }

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Story story = storyRepository.findById(request.getStoryId())
                .orElseThrow(() -> new RuntimeException("Story not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setStory(story);

        Favorite saved = favoriteRepository.save(favorite);

        return mapToResponse(saved);
    }

    @Override
    public List<FavoriteResponse> getFavoritesByUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        return favoriteRepository.findByUser_Id(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void removeFavorite(String username, Long storyId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));
        favoriteRepository.findByUser_IdAndStory_Id(user.getId(), storyId)
                .ifPresent(favoriteRepository::delete);
    }

    private FavoriteResponse mapToResponse(Favorite favorite) {
        FavoriteResponse res = new FavoriteResponse();
        res.setId(favorite.getId());
        res.setUserId(favorite.getUser().getId());
        res.setStoryId(favorite.getStory().getId());
        res.setCreatedAt(favorite.getCreatedAt());
        return res;
    }
}
