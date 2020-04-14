package com.bannirui.spring.security.service.impl;

import com.bannirui.spring.security.config.auth.MyUserDetails;
import com.bannirui.spring.security.dal.mapper.UserMapper;
import com.bannirui.spring.security.dal.model.User;
import com.bannirui.spring.security.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: dingrui
 * Date:   2020/4/14
 * Des:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
