package com.eric.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-08 23:12
 * @since jdk-11.0.14
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer
{
    @Bean
    public IpCountInterceptor ipCountInterceptor()
    {
        return new IpCountInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(ipCountInterceptor()).addPathPatterns("/**");
    }
}
