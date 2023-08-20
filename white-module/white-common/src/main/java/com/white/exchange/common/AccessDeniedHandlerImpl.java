package com.white.exchange.common;

import cn.hutool.json.JSONUtil;
import com.white.exchange.utils.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author
 * @create 2022-10-17  16:35
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ResponseResult<String> result = ResponseResult.echo(HttpStatus.FORBIDDEN.value(), "权限不足");
        String json = JSONUtil.toJsonStr(result);
        WebUtil.renderString(response,json);
    }

}
