package com.white.exchange.filters;


import cn.hutool.core.util.ObjectUtil;
import com.white.exchange.common.CustomException;
import com.white.exchange.pojo.UserDetail;
import com.white.exchange.utils.JwtUtil;
import com.white.exchange.utils.NameOrTimeUtil;
import com.white.exchange.utils.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xh
 * @create 2023-08-20  22:43
 */

@Component
public class CheckLoginFilter extends OncePerRequestFilter {

    private RedisCacheUtil redisCacheUtil;

    @Autowired
    public void setRedisCacheUtil(RedisCacheUtil redisCacheUtil){
        this.redisCacheUtil = redisCacheUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader(NameOrTimeUtil.TOKEN_KEY);
        if(ObjectUtil.isNull(token)){
            filterChain.doFilter(request, response);
            return;
        }

        String id = null;

        //解析token
        try {
            Claims claims = JwtUtil.parseJWT(token);
            id = claims.getSubject();
        } catch (Exception e) {
            request.setAttribute("error_msg", "token非法或过期");
            request.getRequestDispatcher("/custom/error").forward(request, response);
            return;
        }

        UserDetail userDetail = redisCacheUtil.getKey(NameOrTimeUtil.REDIS_LOGIN_PREFIX_CACHE + id, UserDetail.class);

        if(ObjectUtil.isNull(userDetail)){
            request.setAttribute("error_msg", "用户未登录");
            request.getRequestDispatcher("/custom/error").forward(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }


}
