package com.bannirui.spring.security.auth.config;

import com.bannirui.spring.security.auth.handler.MyLogoutSuccessHandler;
import com.bannirui.spring.security.auth.jwt.MyUserDetailsService;
import com.bannirui.spring.security.auth.filter.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * spring security 配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyLogoutSuccessHandler myLogoutSuccessHandler;
    private final MyUserDetailsService myUserDetailsService;
    private final DataSource datasource;
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  // 开启跨域访问
                .ignoringAntMatchers("/auth/register", "/auth/login", "/hello")  // 开启跨域访问的路由
                .and().cors().and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)  // 自定义过滤器
                .logout()
                .logoutUrl("/signout")  // 注销路由
                .deleteCookies("JSESSIONID")  // 注销后删除cookie
                .logoutSuccessHandler(myLogoutSuccessHandler)  // 注销处理器
                .and().rememberMe()  // 记住我功能
                .rememberMeParameter("remember-me-new")
                .rememberMeCookieName("remember-me-cookie")
                .tokenValiditySeconds(2 * 24 * 60 * 60)  // token有效期
                .tokenRepository(persistentTokenRepository())
                .and()
                .authorizeRequests()
                .antMatchers("/auth/login", "/auth/register", "/refreshToken").permitAll()  // 不设置认证和鉴权直接访问路由
                .antMatchers("/index").authenticated()  // 用户通过认证不需要鉴权，访问首页
                .anyRequest().access("@rabcService.hasPermission(request,authentication)")
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        // 将项目中静态资源路径开放出来
        web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(datasource);

        return tokenRepository;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:18001"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
