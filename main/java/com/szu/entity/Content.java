package com.szu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private int id;
    private String title;
    private String coverImage;
    private String contentBody;
    private int authorId;
    private int viewCount;
    private int commentCount;
    private int status;
    private int isDeleted;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
