package com.comicapp.comic_api.service;

import com.comicapp.comic_api.dto.request.FavoriteCreateRequest;
import com.comicapp.comic_api.dto.response.FavoriteResponse;

import java.util.List;

public interface FavoriteService {

    FavoriteResponse addFavorite(FavoriteCreateRequest request);

    List<FavoriteResponse> getFavoritesByUser(String username);

    void removeFavorite(String username, Long storyId);
}