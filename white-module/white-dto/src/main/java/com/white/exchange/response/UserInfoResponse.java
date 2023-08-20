package com.white.exchange.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.white.exchange.pojo.User;
import lombok.Data;

/**
 * @author xh
 * @create 2023-08-20  14:05
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoResponse extends User {

    private String token;
}
