package com.eric.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-05-20 12:06
 * @since jdk-11.0.14
 */
@Configuration
@ComponentScan("com.eric")
@PropertySource("classpath:jdbc.properties")
public class SpringConfig
{
}
