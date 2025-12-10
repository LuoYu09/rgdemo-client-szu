package com.szu.service;

import com.szu.dto.UserLoginDTO;
import com.szu.dto.UserUpdateDTO;
import com.szu.entity.User;

import java.io.IOException;

public interface UserService {

    User login(UserLoginDTO dto) throws IOException;

    User getUserByOpenId(String openid);

    void updateUser(UserUpdateDTO dto);
}
