package com.bannirui.spring.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description: 用户登录实体类
 */
@Data
@AllArgsConstructor
public class UserLoginDto {

    private String username;  // 用户名

    private String password;  // 密码
}
