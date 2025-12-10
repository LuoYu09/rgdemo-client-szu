package com.szu.mapper;

import com.szu.dto.UserUpdateDTO;
import com.szu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM client_user WHERE openid = #{openid}")
    User selectByOpenId(String openid);

    void addUser(User newUser);

    void updateUser(UserUpdateDTO dto);
}
