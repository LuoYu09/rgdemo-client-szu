package com.szu.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserUpdateDTO {
    private String openid;
    private String name;
    private String avatar;
    private String signature;
    private String sex;
    private String area;

    //用于后端更新最后登录时间，前端不传递该字段
    private LocalDateTime lastLoginTime;
}
