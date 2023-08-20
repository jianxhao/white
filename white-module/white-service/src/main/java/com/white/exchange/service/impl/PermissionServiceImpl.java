package com.white.exchange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.white.exchange.mapper.PermissionMapper;
import com.white.exchange.pojo.Permission;
import com.white.exchange.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @author xh
 * @create 2023-08-20  22:34
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
