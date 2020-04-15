package com.bannirui.spring.security.config.auth.jwt;

import com.bannirui.spring.security.config.auth.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private  final JwtTokenUtil jwtTokenUtil;
    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 请求头中获取token
        String jwtToken = request.getHeader(jwtTokenUtil.getHeader());

        // token判空
        if (!StringUtils.isEmpty(jwtToken)) {
            // token中解析出username
            String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            // username不为空并且没有认证过
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                // token有效性判断
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    // 给使用该JWT令牌的用户进行授权
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
