package com.bannirui.spring.security.auth.jwt;

import static org.junit.jupiter.api.Assertions.*;

import com.bannirui.spring.security.auth.dto.UserLoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtAuthServiceTest {
    @Autowired
    private JwtAuthService instance;

    @Test
    void login() {
        UserLoginDto userLoginDto = new UserLoginDto("admin", "admin");
        String token;
        try {
            token = instance.login(userLoginDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
}