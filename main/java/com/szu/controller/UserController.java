package com.szu.controller;

import com.szu.constant.JwtClaimsConstant;
import com.szu.context.BaseContext;
import com.szu.dto.UserLoginDTO;
import com.szu.dto.UserUpdateDTO;
import com.szu.entity.User;
import com.szu.exception.UserNotExistException;
import com.szu.properties.JwtProperties;
import com.szu.result.Result;
import com.szu.service.UserService;
import com.szu.utils.JwtUtil;
import com.szu.vo.UserLoginVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登录
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO dto) throws IOException {
        log.info("用户登录：{}",dto);

        User user = userService.login(dto);

        Map<String,Object> map = new HashMap<>();
        map.put(JwtClaimsConstant.USER_ID,user.getId());
        //System.out.println(user.getId());
        map.put("time", "short");

        String shortToken = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getShortTtl(),map);

        map.remove("time");
        map.put("time", "long");
        String longToken = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getLongTtl(),map);

        UserLoginVO result = UserLoginVO.builder().id(user.getId()).shortToken(shortToken).longToken(longToken).openid(user.getOpenid()).build();

        return Result.success(result);
    }

    @GetMapping("/token")
    public Result<UserLoginVO> getToken(String openid){
        log.info("获取用户token：{}",openid);

        Map<String,Object> map = new HashMap<>();
        map.put(JwtClaimsConstant.USER_ID, BaseContext.getCurrentId());
        map.put("time", "short");

        String shortToken = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getShortTtl(),map);

        map.remove("time");
        map.put("time", "long");
        String longToken = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getLongTtl(),map);

        UserLoginVO result = UserLoginVO.builder().openid(openid).shortToken(shortToken).longToken(longToken).build();

        return Result.success(result);
    }

    @GetMapping("/{openid}")
    public Result<User> getUserByOpenId(@PathVariable String openid){
        log.info("根据openid获取用户信息：{}",openid);

        User user = userService.getUserByOpenId(openid);

        if(user == null){
            throw new UserNotExistException("用户不存在！");
        }

        return Result.success(user);
    }

    @PutMapping()
    public Result updateUserInfo(@RequestBody UserUpdateDTO dto){
        log.info("更新用户信息：{}",dto);

        User user = userService.getUserByOpenId(dto.getOpenid());
        if(user == null){
            throw new UserNotExistException("用户不存在！");
        }

        userService.updateUser(dto);

        return Result.success();
    }
}

