package com.szu.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String openid;
    private String name;
    private String avatar;
    private String signature;
    private String sex;
    private String area;
}
