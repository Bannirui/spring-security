package com.bannirui.spring.security.auth.jwt;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description:
 */
@Data
public class MyUserDetails implements UserDetails {

    private String username;  // 用户名
    private String password;  // 密码
    private boolean accountNonExpired;  // 账户是否过期
    private boolean accountNonLocked;  // 是否被锁定
    private boolean credentialsNonExpired;  // 凭证是否过期
    private boolean enabled;  // 账号是否可用
    private Collection<? extends GrantedAuthority> authorities;  // 用户的权限集合

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
