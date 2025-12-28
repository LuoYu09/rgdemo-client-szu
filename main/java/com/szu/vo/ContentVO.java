package com.szu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentVO {
    private int id;
    private String title;
    private String authorName;
    private String contentBody;
    private int viewCount;
    private int commentCount;
    private double avgRating;
    private int ratingCount;
    private String createdAt;
    private String coverImage;
    private Integer rating;
    private Integer likeCount;
    private Integer like;
    private List<String> images;
    private List<String> tags;
}
