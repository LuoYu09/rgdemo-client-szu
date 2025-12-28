package com.szu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String id;
    private Integer userId;
    private String username;
    private String content;
    private Integer likeCount;
    private Integer status;
    private String createdAt;
    private String replies;
}
