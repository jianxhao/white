package com.white.exchange.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.exchange.common.CustomException;
import com.white.exchange.common.ResponseResult;
import com.white.exchange.mapper.UserMapper;
import com.white.exchange.pojo.User;
import com.white.exchange.pojo.UserDetail;
import com.white.exchange.request.UserLoginRequest;
import com.white.exchange.response.UserInfoResponse;
import com.white.exchange.service.UserService;
import com.white.exchange.utils.JwtUtil;
import com.white.exchange.utils.NameOrTimeUtil;
import com.white.exchange.utils.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author xh
 * @create 2023-08-20  13:09
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService{

    private AuthenticationManager authenticationManager;
    private RedisCacheUtil redisCacheUtil;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;

    }

    @Autowired
    public void setRedisCacheUtil(RedisCacheUtil redisCacheUtil){
        this.redisCacheUtil = redisCacheUtil;
    }

    @Override
    public ResponseResult<UserInfoResponse> userLoginByUsernameAndPassword(UserLoginRequest userLogin) {
        // 验证账号密码
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 获取用户信息
        UserDetail userDetail = (UserDetail)authenticate.getPrincipal();
        User user = userDetail.getUser();
        // 通过id生成token
        String id = String.valueOf(user.getId());
        String token = JwtUtil.createJWT(id);
        // 创建返回对象
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        String[] ignore = {
                "password",
                "updateTime"
        }; // 不包含的属性
        BeanUtil.copyProperties(user, userInfoResponse, ignore);
        userInfoResponse.setToken(token);

        redisCacheUtil.setKey(NameOrTimeUtil.REDIS_LOGIN_PREFIX_CACHE + id, userDetail); // 保存登录信息
        // 返回
        return ResponseResult.success(userInfoResponse);
    }

}
