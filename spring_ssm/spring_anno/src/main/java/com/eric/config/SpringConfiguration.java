package com.eric.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 20:42
 * <p>
 * Configuration 标志改类是Spring核心核心配置文件
 * ComponentScan 相当于<context:component-scan base-package="com.eric"/>
 */
@Configuration
@ComponentScan("com.eric")
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {

}
