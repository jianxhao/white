package com.white.exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.exchange.mapper.UserRoleMapper;
import com.white.exchange.pojo.UserRole;
import com.white.exchange.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author xh
 * @create 2023-08-20  21:53
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
