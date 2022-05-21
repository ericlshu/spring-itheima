package com.eric.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Description :
 *
 * @author Eric SHU
 */

public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]{SpringMVCConfig.class};
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }
}
/*
public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer
{
    // 加载springmvc配置类，产生springmvc容器（本质还是spring容器）
    protected WebApplicationContext createServletApplicationContext()
    {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMVCConfig.class);
        return context;
    }
    // 加载spring配置类
    protected WebApplicationContext createRootApplicationContext()
    {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);
        return context;
    }
    // 设置由springmvc控制器处理的请求映射路径
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }
}
*/
