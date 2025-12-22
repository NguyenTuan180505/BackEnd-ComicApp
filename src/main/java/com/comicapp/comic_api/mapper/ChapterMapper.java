package com.comicapp.comic_api.mapper;

import com.comicapp.comic_api.dto.request.ChapterCreateRequest;
import com.comicapp.comic_api.dto.response.ChapterDetailResponse;
import com.comicapp.comic_api.dto.response.ChapterResponse;
import com.comicapp.comic_api.entity.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    ChapterMapper INSTANCE = Mappers.getMapper(ChapterMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "story", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Chapter toEntity(ChapterCreateRequest request);
    @Mapping(target = "storyId", source = "story.id")
    ChapterResponse toResponse(Chapter chapter);

    @Mapping(target = "storyId", source = "story.id")
    ChapterDetailResponse toDetailResponse(Chapter chapter); // detail

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "story", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(ChapterCreateRequest request, @MappingTarget Chapter chapter);
}