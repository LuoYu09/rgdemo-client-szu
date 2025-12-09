package com.szu.mapper;

import com.szu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE openid = #{openid} AND is_deleted = '0' LIMIT 1")
    User selectByOpenId(String openid);

    void addUser(User newUser);
}
