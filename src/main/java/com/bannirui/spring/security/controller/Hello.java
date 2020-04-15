/**
 * -------------------------------------------------
 * File Name：      Hello
 * Author:          dingrui
 * Date：           2020/4/14
 * Function:
 * -------------------------------------------------
 */

package com.bannirui.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/hello")
    public String hello() {
        return "hello bannirui";
    }
}
