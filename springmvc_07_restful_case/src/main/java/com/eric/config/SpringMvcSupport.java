package com.eric.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Description :
 *
 * @author Eric SHU
 */
@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport
{
    /**
     * 设置静态资源访问过滤，当前类需要设置为配置类，并被扫描加载
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("/plugins/");
    }
}
