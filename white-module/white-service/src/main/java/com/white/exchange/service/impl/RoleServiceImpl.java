package com.white.exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.exchange.mapper.RoleMapper;
import com.white.exchange.pojo.Role;
import com.white.exchange.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author xh
 * @create 2023-08-20  21:39
 */

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
