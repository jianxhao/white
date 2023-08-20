package com.white.exchange.common;

import lombok.AllArgsConstructor;

/**
 * @author xh
 * @create 2023-08-14  10:49
 */
@AllArgsConstructor
public enum HttpCodeEnum {

    SUCCESS(200, "操作成功"),
    FAIL(201, "操作失败"),
    AUTHORITY_NOT(401, "权限不足"),
    SYSTEM_AUTHORITY_NOT(403, "服务器拒绝访问");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
