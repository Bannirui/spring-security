package com.bannirui.spring.security.dal.mapper;

import com.bannirui.spring.security.config.auth.MyUserDetails;
import com.bannirui.spring.security.dal.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author: dingrui
 * Date:   2020/4/14
 * Des:
 */
public interface UserMapper extends BaseMapper<User> {

}
