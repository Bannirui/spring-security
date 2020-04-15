package com.bannirui.spring.security.auth.service.impl;

import com.bannirui.spring.security.auth.dal.mapper.RoleMapper;
import com.bannirui.spring.security.auth.dal.model.Role;
import com.bannirui.spring.security.auth.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Author: dingrui
 * Date:   2020/4/14
 * Des:
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
