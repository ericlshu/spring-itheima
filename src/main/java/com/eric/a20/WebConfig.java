package com.eric.a20;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties({WebMvcProperties.class, ServerProperties.class})
public class WebConfig
{
    /**
     * ⬅️内嵌 Tomcat web 容器工厂
     */
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(ServerProperties serverProperties)
    {
        return new TomcatServletWebServerFactory(serverProperties.getPort());
    }

    /**
     * ⬅️创建 DispatcherServlet
     */
    @Bean
    public DispatcherServlet dispatcherServlet()
    {
        return new DispatcherServlet();
    }

    /**
     * ⬅️注册 DispatcherServlet, Spring MVC 的入口
     */
    @Bean
    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(
            DispatcherServlet dispatcherServlet, WebMvcProperties webMvcProperties)
    {
        DispatcherServletRegistrationBean registrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        // DispatcherServlet默认在第一次访问web容器时创建，使用以下方法可以设置为tomcat服务器启动时创建并初始化，数字越小初始化优先级越小
        // registrationBean.setLoadOnStartup(1);
        // 也可以使用以下方式加载配置文件中的对应配置属性，避免在代码中hard code
        registrationBean.setLoadOnStartup(webMvcProperties.getServlet().getLoadOnStartup());
        return registrationBean;
    }

    /**
     * 如果用 DispatcherServlet 初始化时默认添加的组件, requestMappingHandlerMapping 作为 DispatcherServlet 的成员变量存在，
     * 并不会作为 bean, 给测试带来困扰，所以选择手动创建并加入spring容器，容器中存在则不会再加载默认值作为 DispatcherServlet 的属性
     * ⬅️1. 加入RequestMappingHandlerMapping
     */
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping()
    {
        return new RequestMappingHandlerMapping();
    }

    /**
     * ⬅️2. 继续加入RequestMappingHandlerAdapter, 会替换掉 DispatcherServlet 默认的 4 个 HandlerAdapter
     */
    @Bean
    public MyRequestMappingHandlerAdapter requestMappingHandlerAdapter()
    {
        MyRequestMappingHandlerAdapter handlerAdapter = new MyRequestMappingHandlerAdapter();
        handlerAdapter.setCustomArgumentResolvers(List.of(new TokenArgumentResolver()));
        return handlerAdapter;
    }
}
