package com.bannirui.spring.security.dto;

import lombok.Data;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description: 用户登录实体类
 */
@Data
public class UserLoginDto {

    private String username;  // 用户名

    private String password;  // 密码
}
