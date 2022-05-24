package com.eric.config;

import com.eric.controller.interceptor.ProjectInterceptor1;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric SHU
 */
// @Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport
{
    @Resource
    private ProjectInterceptor1 projectInterceptor;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(projectInterceptor).addPathPatterns("/books", "/books/*");
    }
}
