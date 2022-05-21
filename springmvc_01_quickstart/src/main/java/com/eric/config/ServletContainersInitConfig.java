package com.eric.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

/**
 * Description :
 * AbstractDispatcherServletInitializer类是SpringMVC提供的快速初始化Web3.0容器的抽象类
 * AbstractDispatcherServletInitializer提供了三个接口方法供用户实现
 * -->> createServletApplicationContext方法，创建Servlet容器时，加载SpringMVC对应的bean并放入WebApplicationContext对象范围中，而WebApplicationContext的作用范围为ServletContext范围，即整个web容器范围
 * -->> getServletMappings方法，设定SpringMVC对应的请求映射路径，即SpringMVC拦截哪些请求
 * -->> createRootApplicationContext方法，如果创建Servlet容器时需要加载非SpringMVC对应的bean,使用当前方法进行，使用方式和createServletApplicationContext相同。
 * -->> createServletApplicationContext用来加载SpringMVC环境
 * -->> createRootApplicationContext用来加载Spring环境
 *
 * @author Eric SHU
 */
public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer
{
    /**
     * 加载springmvc配置类，产生springmvc容器（本质还是spring容器）
     */
    protected WebApplicationContext createServletApplicationContext()
    {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMVCConfig.class);
        return context;
    }

    /**
     * 设置由springmvc控制器处理的请求映射路径
     */
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }

    /**
     * 加载spring配置类
     */
    protected WebApplicationContext createRootApplicationContext()
    {
        return null;
    }
}
