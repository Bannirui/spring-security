package com.bannirui.spring.security.auth.service.impl;

import com.bannirui.spring.security.auth.dal.mapper.RoleMenuMapper;
import com.bannirui.spring.security.auth.dal.model.RoleMenu;
import com.bannirui.spring.security.auth.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Author: dingrui
 * Date:   2020/4/14
 * Des:
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
}
