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

    //姓名
    private String username;

    //昵称
    private String nickname;

    //手机号
    private String phone;

    //状态 0：禁用 1：正常
    private int status;

    //是否删除 0：未删除 1：已删除
    private String isDeleted;

    //头像
    private String avatar;

    //注册时间
    private LocalDateTime createAt;

    //更新时间
    private LocalDateTime updateAt;

    //角色
    private int role;
}

