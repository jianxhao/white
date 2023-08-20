package com.white.exchange.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.white.exchange.common.ResponseResult;
import com.white.exchange.pojo.User;
import com.white.exchange.request.UserLoginRequest;
import com.white.exchange.response.UserInfoResponse;

/**
 * @author xh
 * @create 2023-08-20  13:09
 */
public interface UserService extends IService<User> {
    ResponseResult<UserInfoResponse> userLoginByUsernameAndPassword(UserLoginRequest userLogin);
}
