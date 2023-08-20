package com.white.exchange.common;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xh
 * @create 2023-08-20  13:32
 */

@RestControllerAdvice(annotations = {Service.class, RestController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseResult<String> customException(CustomException e){
        return ResponseResult.fail(e.getMessage());
    }

}
