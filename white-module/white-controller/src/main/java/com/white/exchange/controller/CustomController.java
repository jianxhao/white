package com.white.exchange.controller;

import com.white.exchange.common.CustomException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xh
 * @create 2023-08-21  0:25
 */
@RestController
@RequestMapping("/custom")
public class CustomController {

    @RequestMapping("/error")
    public void CustomExceptionHandler(HttpServletRequest request){
        String errorMsg = request.getAttribute("error_msg").toString();
        throw new CustomException(errorMsg);
    }
}
