package com.bannirui.spring.security.service.impl;

import com.bannirui.spring.security.dal.mapper.MenuMapper;
import com.bannirui.spring.security.dal.model.Menu;
import com.bannirui.spring.security.service.MenuService;
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
