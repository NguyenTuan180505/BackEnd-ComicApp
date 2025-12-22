package com.comicapp.comic_api.mapper;

import com.comicapp.comic_api.dto.response.UserPointsResponse;
import com.comicapp.comic_api.entity.UserPoints;
import org.springframework.stereotype.Component;

@Component
public class UserPointsMapper {

    public UserPointsResponse toResponse(UserPoints userPoints){
        UserPointsResponse userPointsResponse = new UserPointsResponse();
        userPointsResponse.setUserId(userPoints.getUser().getId());
        userPointsResponse.setPoints(userPoints.getPoints());
        userPointsResponse.setId(userPoints.getId());
        return userPointsResponse;
    }
}
