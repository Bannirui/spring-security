package com.bannirui.spring.security.service.impl;

import com.bannirui.spring.security.dal.mapper.RoleMenuMapper;
import com.bannirui.spring.security.dal.model.RoleMenu;
import com.bannirui.spring.security.service.RoleMenuService;
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
