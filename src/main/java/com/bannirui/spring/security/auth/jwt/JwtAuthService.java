package com.bannirui.spring.security.auth.jwt;

import com.bannirui.spring.security.auth.utils.JwtTokenUtil;
import com.bannirui.spring.security.vo.response.CustomException;
import com.bannirui.spring.security.vo.response.CustomExceptionType;
import com.bannirui.spring.security.auth.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description: 实现类 登录认证换取token/刷新token
 */

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class JwtAuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    /**
     * 登录认证换取JWT令牌
     *
     * @return JWT
     */
    public String login(UserLoginDto userLoginDto) throws CustomException {
        try {
            // 对账号密码进行认证
            UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword());
            // 执行抛出异常 User is disabled
            System.out.println(authenticationManager);
            Authentication authentication = authenticationManager.authenticate(usernamePasswordToken);
            System.out.println("=========================测试=========================");
            System.out.println(authentication);
            // 首次进行认证 之前没有被认证过 将认证信息存入上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            // 未通过认证 账号密码不正确
            throw new CustomException(CustomExceptionType.USER_INPUT_ERROR, "用户名或者密码不正确");
        }

        // spring security接口
        UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginDto.getUsername());
        // 生成token
        return jwtTokenUtil.generateToken(userDetails);
    }


    /**
     * 刷新token
     *
     * @param oldToken
     * @return
     */
    public String refreshToken(String oldToken) {
        // 若token未过期则刷新 若token已过期则返回空
        if (!jwtTokenUtil.isTokenExpired(oldToken)) {
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }
}
