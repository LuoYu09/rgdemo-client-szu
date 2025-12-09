package com.szu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.szu.dto.UserLoginDTO;
import com.szu.entity.User;
import com.szu.exception.LoginFailedException;
import com.szu.mapper.UserMapper;
import com.szu.properties.WeChatProperties;
import com.szu.service.UserService;
import com.szu.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param dto
     * @return
     */
    @Override
    public User login(UserLoginDTO dto) throws IOException {
        Map<String,String> map = new HashMap<>();

        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",dto.getCode());
        map.put("grant_type","authorization_code");

        String result = HttpClientUtil.doGet("https://api.weixin.qq.com/sns/jscode2session",map);

        JSONObject json = JSONObject.parseObject(result);
        String openid = (String) json.get("openid");

        if(openid == null){
            throw new LoginFailedException("登录失败");
        }

        User user = userMapper.selectByOpenId(openid);

        if(user == null){
            User newUser = new User();

            newUser.setOpenid(openid);
            newUser.setNickname("未命名");
            newUser.setPhone("未设置");
            newUser.setStatus(1);
            newUser.setIsDeleted("0");
            newUser.setRole(0);
            newUser.setCreateAt(LocalDateTime.now());
            newUser.setUpdateAt(LocalDateTime.now());
            userMapper.addUser(newUser);

            return newUser;
        }

        return user;
    }
}
