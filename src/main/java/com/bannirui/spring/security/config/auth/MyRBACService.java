package com.bannirui.spring.security.config.auth;

import com.bannirui.spring.security.dal.model.*;
import com.bannirui.spring.security.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component("rabcService")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class MyRBACService {

    private UserService userService;
    private UserRoleService userRoleService;
    private RoleService roleService;
    private RoleMenuService roleMenuService;
    private MenuService menuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    /**
     * 判断某用户是否具有该request资源的访问权限
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            // 获取用户名
            String username = ((UserDetails) principal).getUsername();

            // 根据用户名查询数据库 username->user_id->role_id->menu_id->url
            List<String> urls = menuService.list(new LambdaQueryWrapper<Menu>().in(Menu::getId,
                                    roleMenuService.list(new LambdaQueryWrapper<RoleMenu>().in(RoleMenu::getRoleId,
                                                            userRoleService.list(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserId,
                                                                    userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username)).getId()))
                                                            .stream().map(UserRole::getRoleId).collect(Collectors.toList())))
                                    .stream().map(RoleMenu::getMenuId).collect(Collectors.toList())))
                    .stream().map(Menu::getUrl).collect(Collectors.toList());

            // 判断该
            return urls.stream().anyMatch(url -> antPathMatcher.match(url,request.getRequestURI()));

        }

        return false;
    }


}
