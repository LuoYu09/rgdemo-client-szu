package com.szu.dto;

import lombok.Data;

@Data
public class AddRatingDTO {
    //文章ID
    private Integer contentId;

    //文章评分
    private Integer rating;

    //用户ID
    private Integer userId;
}
