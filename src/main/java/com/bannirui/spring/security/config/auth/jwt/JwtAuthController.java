package com.bannirui.spring.security.config.auth.jwt;

import com.bannirui.spring.security.config.exception.AjaxResponse;
import com.bannirui.spring.security.config.exception.CustomException;
import com.bannirui.spring.security.config.exception.CustomExceptionType;
import com.bannirui.spring.security.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description: 路由函数 登录/刷新token
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class JwtAuthController {

    @PostMapping("/login")
    public AjaxResponse login(@RequestBody UserLoginDto userLoginDto) {
        // 前端登录入参校验
        if(StringUtils.isEmpty(userLoginDto.getUsername()) || StringUtils.isEmpty(userLoginDto.getPassword())) {
            return AjaxResponse.error(
                    new CustomException(CustomExceptionType.USER_INPUT_ERROR, "用户名或密码不能为空")
            );
        }

        // 账号换取token
    }
}
