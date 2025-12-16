package com.szu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //微信用户唯一标识
    private String openid;

    //昵称
    private String name;

    //头像
    private String avatar;

    //个性签名
    private String signature;

    //性别
    private String sex;

    //地区
    private String area;

    //注册时间
    private LocalDateTime createTime;

    //更新时间
    private LocalDateTime updateTime;

    //最后登录时间
    private LocalDateTime lastLoginTime;
}

