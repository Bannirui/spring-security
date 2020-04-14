package com.bannirui.spring.security.service.impl;

import com.bannirui.spring.security.dal.mapper.RoleMapper;
import com.bannirui.spring.security.dal.mapper.RoleMenuMapper;
import com.bannirui.spring.security.dal.model.Role;
import com.bannirui.spring.security.service.RoleService;
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
