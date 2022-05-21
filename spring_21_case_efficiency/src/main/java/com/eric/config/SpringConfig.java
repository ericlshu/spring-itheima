package com.eric.config;

import org.springframework.context.annotation.*;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-05-21 11:32
 * @since jdk-11.0.14
 */
@Configuration
@ComponentScan("com.eric")
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MybatisConfig.class})
@EnableAspectJAutoProxy
public class SpringConfig
{
}
