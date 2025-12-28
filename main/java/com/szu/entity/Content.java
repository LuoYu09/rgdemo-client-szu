package com.szu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    private String tags;

    private String likes;

    private String images;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
