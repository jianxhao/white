package com.white.exchange.common;

import cn.hutool.json.JSONUtil;
import com.white.exchange.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xh
 * @create 2023-08-21  0:17
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult<String> result = ResponseResult.fail("验证失败");
        String json = JSONUtil.toJsonStr(result);
        WebUtil.renderString(response,json);
    }
}
