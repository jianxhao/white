package com.white.exchange.controller;

import com.white.exchange.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xh
 * @create 2023-08-20  21:37
 */

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService){
        this.roleService = roleService;
    }

}
