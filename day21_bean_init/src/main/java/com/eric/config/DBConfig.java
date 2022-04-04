package com.eric.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-04 17:14
 * @since jdk-11.0.14
 */
// @Component
@Configuration
public class DBConfig
{
    @Bean
    public DruidDataSource druidDataSource()
    {
        return new DruidDataSource();
    }
}
