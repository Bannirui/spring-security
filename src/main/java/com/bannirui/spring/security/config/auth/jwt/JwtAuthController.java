package com.bannirui.spring.security.config.auth.jwt;

import com.bannirui.spring.security.config.exception.AjaxResponse;
import com.bannirui.spring.security.config.exception.CustomException;
import com.bannirui.spring.security.config.exception.CustomExceptionType;
import com.bannirui.spring.security.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description: 路由函数 登录/刷新token
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class JwtAuthController {

    private JwtAuthService jwtAuthService;

    /**
     * 登录
     * @param userLoginDto
     * @return
     */
    @PostMapping("/login")
    public AjaxResponse login(@RequestBody UserLoginDto userLoginDto) {
        // 前端登录入参校验
        if(StringUtils.isEmpty(userLoginDto.getUsername()) || StringUtils.isEmpty(userLoginDto.getPassword())) {
            return AjaxResponse.error(
                    new CustomException(CustomExceptionType.USER_INPUT_ERROR, "用户名或密码不能为空")
            );
        }

        // 账号换取token
        try {
            return AjaxResponse.success(jwtAuthService.login(userLoginDto));
        } catch (CustomException e) {
            return AjaxResponse.error(e);
        }
    }

    @GetMapping("/refreshToken")
    public AjaxResponse refreshToken(@RequestHeader("${jwt.header}") String token) {
        return AjaxResponse.success(jwtAuthService.refreshToken(token));
    }
}
