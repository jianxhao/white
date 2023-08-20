package com.white.exchange.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xh
 * @create 2023-08-14  10:43
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable{

    // 状态码
    private Integer code;

    // msg
    private String msg;

    // 数据
    private T data;

    // 默认成功
    public ResponseResult(){
        this(HttpCodeEnum.SUCCESS); // 默认成功
    }

    // 自定义
    public ResponseResult(HttpCodeEnum httpCodeEnum){
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

    // 自定义 + 数据
    public ResponseResult(HttpCodeEnum httpCodeEnum, T data){
        this(httpCodeEnum);
        this.data = data;
    }

    // 数据
    public ResponseResult(T data){
        this(HttpCodeEnum.SUCCESS, data);
    }

    // 手动输入
    public ResponseResult(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }


    //方法
    // 成功返回
    public static ResponseResult success(){
        ResponseResult success = new ResponseResult();
        return success;
    }

    // 失败返回
    public static ResponseResult fail(){
        ResponseResult fail = new ResponseResult(HttpCodeEnum.FAIL);
        return fail;
    }

    // 自定义enum
    public static ResponseResult echo(HttpCodeEnum httpCodeEnum){
        ResponseResult echo = new ResponseResult(httpCodeEnum);
        return echo;
    }

    // 自定义msg
    public static ResponseResult success(String msg){
        ResponseResult success = new ResponseResult(200, msg);
        return success;
    }

    public static ResponseResult fail(String msg){
        ResponseResult fail = new ResponseResult(201, msg);
        return fail;
    }

    public static ResponseResult echo(Integer code, String msg){
        ResponseResult echo = new ResponseResult(code, msg);
        return echo;
    }

    // 数据
    // 成功 + 数据
    public static <T> ResponseResult<T> success(T data){
        ResponseResult<T> success = new ResponseResult<>(data);
        return success;
    }

}
