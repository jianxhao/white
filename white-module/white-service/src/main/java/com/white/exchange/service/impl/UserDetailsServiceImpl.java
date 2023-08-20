package com.white.exchange.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.white.exchange.common.CustomException;
import com.white.exchange.mapper.UserMapper;
import com.white.exchange.pojo.*;
import com.white.exchange.service.PermissionService;
import com.white.exchange.service.UserRoleService;
import com.white.exchange.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xh
 * @create 2023-08-20  14:22
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserMapper userMapper;
    private PermissionService permissionService;
    private UserRoleService userRoleService;
    private RolePermissionService rolePermissionService;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserRoleService(RolePermissionService rolePermissionService){
        this.rolePermissionService = rolePermissionService;
    }

    @Autowired
    public void setRolePermissionService(UserRoleService userRoleService){
        this.userRoleService = userRoleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService){
        this.permissionService = permissionService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取判断数据库是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);

        if(ObjectUtil.isNull(user)){
            throw new CustomException("账号不存在");
        }
        // 获取角色id
        List<UserRole> userRoles = userRoleService.lambdaQuery()
                .select(UserRole::getRoleId)
                .eq(UserRole::getUserId, user.getId())
                .list();
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 获取权限id
        List<RolePermission> rolePermissions = rolePermissionService.lambdaQuery()
                .select(RolePermission::getPermissionId)
                .in(RolePermission::getRoleId, roleIds)
                .list();
        List<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        // 获取权限信息
        List<Permission> permissions = permissionService.lambdaQuery()
                .select(Permission::getName)
                .in(Permission::getId, permissionIds)
                .list();
        List<String> permissionList = permissions.stream().map(Permission::getName).collect(Collectors.toList());
        // 返回UserDetails对象
        UserDetail userDetail = new UserDetail(user, permissionList);

        return userDetail;
    }
}
