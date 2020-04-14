package com.bannirui.spring.security.service.impl;

import com.bannirui.spring.security.dal.mapper.UserRoleMapper;
import com.bannirui.spring.security.dal.model.UserRole;
import com.bannirui.spring.security.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Author: dingrui
 * Date:   2020/4/14
 * Des:
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
