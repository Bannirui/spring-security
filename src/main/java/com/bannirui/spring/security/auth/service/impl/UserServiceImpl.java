package com.bannirui.spring.security.auth.service.impl;

import com.bannirui.spring.security.auth.dal.mapper.UserMapper;
import com.bannirui.spring.security.auth.dal.model.User;
import com.bannirui.spring.security.auth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Author: dingrui
 * Date:   2020/4/14
 * Des:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
