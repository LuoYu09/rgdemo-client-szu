package com.szu.controller;

import com.szu.constant.JwtClaimsConstant;
import com.szu.dto.UserLoginDTO;
import com.szu.entity.User;
import com.szu.properties.JwtProperties;
import com.szu.result.Result;
import com.szu.service.UserService;
import com.szu.utils.JwtUtil;
import com.szu.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getUserTtl(),map);

        UserLoginVO result = UserLoginVO.builder().id(user.getId()).token(token).openid(user.getOpenid()).build();

        return Result.success(result);
    }
}

