package com.bannirui.spring.security.auth.service.impl;

import com.bannirui.spring.security.auth.dal.mapper.MenuMapper;
import com.bannirui.spring.security.auth.dal.model.Menu;
import com.bannirui.spring.security.auth.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Author: dingrui
 * Date:   2020/4/14
 * Des:
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
