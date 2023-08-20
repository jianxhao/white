package com.white.exchange.controller;

import com.white.exchange.common.ResponseResult;
import com.white.exchange.request.UserLoginRequest;
import com.white.exchange.response.UserInfoResponse;
import com.white.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xh
 * @create 2023-08-20  13:11
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    /**
     * 登录接口
     * @param userLogin 账号和密码
     * @return token + 基本信息
     */
    @PostMapping("/login")
    public ResponseResult<UserInfoResponse> userLoginByUsernameAndPasswordAPI(@RequestBody UserLoginRequest userLogin){
        return userService.userLoginByUsernameAndPassword(userLogin);
    }
}
