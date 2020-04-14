/**
 * -------------------------------------------------
 * File Name：      DatasourceConfiguration
 * Author:          dingrui
 * Date：           2020/2/25
 * Function:
 * -------------------------------------------------
 */

package com.bannirui.spring.security.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * 配置datasource到ioc容器里面
 */
@Configuration
public class DatasourceConfiguration {

    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String jdbcUsername;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;


    /**
     * 生成与spring-dao.xml对应的bean dataSource
     *
     * @return
     */
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

}