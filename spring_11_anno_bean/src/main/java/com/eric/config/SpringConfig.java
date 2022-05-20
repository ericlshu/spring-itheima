package com.eric.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Description :
 * -> @Configuration注解用于设定当前类为配置类
 * -> @ComponentScan注解用于设定扫描路径，此注解只能添加一次，多个数据请用数组格式
 *
 * @author Eric L SHU
 * @date 2022-05-20 12:06
 * @since jdk-11.0.14
 */
@Configuration
// @ComponentScan("com.eric")
@ComponentScan({"com.eric.dao", "com.eric.service"})
public class SpringConfig
{
}
