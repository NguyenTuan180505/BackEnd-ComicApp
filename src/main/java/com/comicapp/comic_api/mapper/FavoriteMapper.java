package com.comicapp.comic_api.mapper;

import com.comicapp.comic_api.dto.response.FavoriteResponse;
import com.comicapp.comic_api.entity.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "story.id", target = "storyId")
    FavoriteResponse toResponse(Favorite favorite);
}
