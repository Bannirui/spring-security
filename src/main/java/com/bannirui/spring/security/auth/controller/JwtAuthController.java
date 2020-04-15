package com.bannirui.spring.security.auth.controller;

import com.bannirui.spring.security.auth.dal.model.User;
import com.bannirui.spring.security.auth.jwt.JwtAuthService;
import com.bannirui.spring.security.auth.service.UserService;
import com.bannirui.spring.security.vo.response.AjaxResponse;
import com.bannirui.spring.security.vo.response.CustomException;
import com.bannirui.spring.security.vo.response.CustomExceptionType;
import com.bannirui.spring.security.auth.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description: 路由函数 登录/刷新token
 */
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class JwtAuthController {

    private final JwtAuthService jwtAuthService;
    private final UserService userService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @PostMapping("/register")
    public String register(@RequestBody UserLoginDto userLoginDto) {
        // todo 参数校验
        User user = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setUsername(userLoginDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userLoginDto.getPassword()));
        System.out.println(user);
        return userService.save(user) ? "注册成功" : "注册失败";
    }
}
