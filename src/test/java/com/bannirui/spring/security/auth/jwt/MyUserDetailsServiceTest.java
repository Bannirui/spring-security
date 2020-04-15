package com.bannirui.spring.security.auth.jwt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyUserDetailsServiceTest {
    @Autowired
    private MyUserDetailsService instance;

    @Test
    void loadUserByUsername() {
        UserDetails userDetails = instance.loadUserByUsername("admin");
        System.out.println(userDetails);
    }
}