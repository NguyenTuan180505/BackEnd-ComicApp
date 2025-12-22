package com.comicapp.comic_api.mapper;

import com.comicapp.comic_api.dto.response.StoryResponse;
import com.comicapp.comic_api.entity.Story;
import org.springframework.stereotype.Component;

@Component
public class StoryMapper {

    public StoryResponse toResponse(Story story){
        StoryResponse storyResponse  = new StoryResponse();
        storyResponse.setId(story.getId());
        storyResponse.setAuthor(story.getAuthor());
        storyResponse.setTitle(story.getTitle());
        storyResponse.setCoverImage(story.getCoverImage());
        storyResponse.setDescription(story.getDescription());
        return storyResponse;
    }
}
