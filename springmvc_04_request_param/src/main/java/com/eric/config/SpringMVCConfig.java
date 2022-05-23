package com.eric.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Description :
 * 开启json数据类型自动转换:@EnableWebMvc
 * @author Eric SHU
 */
@Configuration
@ComponentScan("com.eric.controller")
@EnableWebMvc
public class SpringMVCConfig
{
}
