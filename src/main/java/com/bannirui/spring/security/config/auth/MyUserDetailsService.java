package com.bannirui.spring.security.config.auth;

import com.bannirui.spring.security.dal.model.*;
import com.bannirui.spring.security.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: dingrui
 * @Date: Create in 2020/4/15
 * @Description:
 */
@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final MenuService menuService;
    private final RoleMenuService roleMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 加载基础用户信息 根据用户名查询出用户信息，再转换为MyUserDetails
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        MyUserDetails myUserDetails = user.convert2MyUserDetails();

        // 加载用户角色列表 根据上面用户信息的user_id，再查出role_id，再查出role
        List<Long> roleIds = userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId, user.getId())).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 角色是特殊的权限，所有角色加上ROLE_作为前缀
        List<String> roleCodes = roleService.list(new LambdaQueryWrapper<Role>().in(Role::getId, roleIds)).stream().map(role -> "ROLE_" + role.getRoleCode()).collect(Collectors.toList());

        // 加载资源列表 根据role_id查询出menu_id，再根据menu_id查询出资源路由
        List<Long> menuIds = roleMenuService.list(new LambdaQueryWrapper<RoleMenu>().in(RoleMenu::getRoleId, roleIds)).stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<String> urls = menuService.list(new LambdaQueryWrapper<Menu>().in(Menu::getId, menuIds)).stream().map(Menu::getUrl).collect(Collectors.toList());

        // 角色是权限的一种，将角色加到权限中
        urls.addAll(roleCodes);

        myUserDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(",", urls)
                )
        );

        return myUserDetails;
    }
}
