package com.white.exchange.controller;

import com.white.exchange.common.ResponseResult;
import com.white.exchange.pojo.UserRole;
import com.white.exchange.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xh
 * @create 2023-08-20  21:54
 */
@RestController
@RequestMapping("/api/ur")
public class UserRoleController {

    private UserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService){
        this.userRoleService = userRoleService;
    }

}
