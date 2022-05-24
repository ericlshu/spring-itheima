package com.eric.config;

import com.eric.controller.interceptor.ProjectInterceptor1;
import com.eric.controller.interceptor.ProjectInterceptor2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 实现WebMvcConfigurer接口可以简化开发，但具有一定的侵入性
 */
@Configuration
// @ComponentScan({"com.eric.controller", "com.eric.config"})
@ComponentScan({"com.eric.controller"})
@EnableWebMvc
public class SpringMVCConfig implements WebMvcConfigurer
{
    @Resource
    private ProjectInterceptor1 projectInterceptor1;

    @Resource
    private ProjectInterceptor2 projectInterceptor2;

    /**
     * -> 当配置多个拦截器时，形成拦截器链
     * -> 拦截器链的运行顺序参照拦截器添加顺序为准
     * -> 当拦截器中出现对原始处理器的拦截，后面的拦截器均终止运行
     * -> 当拦截器运行中断，仅运行配置在前面的拦截器的afterCompletion操作
     * -> preHandle：与配置顺序相同，必定运行
     * -> postHandle:与配置顺序相反，可能不运行
     * -> afterCompletion:与配置顺序相反，可能不运行。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(projectInterceptor1).addPathPatterns("/books", "/books/*");
        registry.addInterceptor(projectInterceptor2).addPathPatterns("/books", "/books/*");
    }
}
