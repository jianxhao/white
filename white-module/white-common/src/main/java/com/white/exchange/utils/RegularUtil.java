package com.white.exchange.utils;

/**
 * @author xh
 * @create 2023-08-14  11:41
 */
public class RegularUtil {

    // 帐号(字母开头，允许6-12字节，允许字母数字下划线)
    public final static String USERNAME_REG_EXP = "^[a-zA-Z][a-zA-Z0-9_]{5,11}$";

    // 密码(字母开头，长度在6~18之间，只能包含字母、数字和下划线)
    public final static String PASSWORD_REG_EXP = "^[a-zA-Z]\\w{5,17}$";
}
