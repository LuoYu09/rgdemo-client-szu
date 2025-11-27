package com.szu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentVO {
    private int id;
    private String title;
    private int authorId;
    private String contentBody;
    private int viewCount;
    private int commentCount;
    private double avgRating;
    private int ratingCount;
    private String createdAt;
    private String coverImage;
    private List<String> tags;
}
