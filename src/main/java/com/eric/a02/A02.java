package com.eric.a02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

/*
 * 常见 ApplicationContext 实现
 * -> a. 常见的 ApplicationContext 容器实现
 * -> b. 内嵌容器、DispatcherServlet 的创建方法、作用
 */
@Slf4j
@SuppressWarnings("all")
public class A02
{
    public static void main(String[] args)
    {
        // testClassPathXmlApplicationContext();
        // testFileSystemXmlApplicationContext();
        // testAnnotationConfigApplicationContext();
        testAnnotationConfigServletWebServerApplicationContext();

        // ApplicationContext借助BeanFactory中的BeanDefinitionReader读取需要加载的bean
        // DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // for (String name : beanFactory.getBeanDefinitionNames())
        // {
        //     log.debug("bean name : {}", name);
        // }
        // log.info("/********************************************************************************/");
        // XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // reader.loadBeanDefinitions(new FileSystemResource("src\\main\\resources\\a02.xml"));
        // for (String name : beanFactory.getBeanDefinitionNames())
        // {
        //     log.debug("bean name : {}", name);
        // }
    }

    // ⬇️较为经典的容器, 基于 classpath 下 xml 格式的配置文件来创建
    private static void testClassPathXmlApplicationContext()
    {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("a02.xml");
        printBeans(context);
    }

    /**
     * ⬇️基于磁盘路径下 xml 格式的配置文件来创建
     * ⬇️可以使用绝对磁盘路径或当前工作路径的相对路径
     */
    private static void testFileSystemXmlApplicationContext()
    {
        FileSystemXmlApplicationContext context =
                new FileSystemXmlApplicationContext("src\\main\\resources\\a02.xml");
        printBeans(context);
    }

    /**
     * ⬇️较为经典的容器, 基于 java 配置类来创建Bean
     * ⬇️会将配置Bean后处理器类和配置类一起加入容器，相当于在xml配置文件中使用<context:annotation-config/>
     */
    private static void testAnnotationConfigApplicationContext()
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        printBeans(context);
    }

    // ⬇️较为经典的容器, 基于 java 配置类来创建, 适用于 web 环境
    private static void testAnnotationConfigServletWebServerApplicationContext()
    {
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
        printBeans(context);
    }

    private static void printBeans(ApplicationContext context)
    {
        for (String name : context.getBeanDefinitionNames())
        {
            log.info("bean name : {}", name);
        }
        log.info(context.getBean(Bean2.class).getBean1().toString());
    }

    @Configuration
    static class WebConfig
    {
        // 内嵌基于servlet的web容器，通过工厂创建
        @Bean
        public ServletWebServerFactory servletWebServerFactory()
        {
            return new TomcatServletWebServerFactory();
        }

        // web容器的入口，前端控制器
        @Bean
        public DispatcherServlet dispatcherServlet()
        {
            return new DispatcherServlet();
        }

        // 将前端控制器注册到Tomcat服务器
        @Bean
        public DispatcherServletRegistrationBean registrationBean(DispatcherServlet dispatcherServlet)
        {
            return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        }

        // 具体的控制器，Controller接口而非注解，实现handleRequest方法，访问路径为@Bean中的参数
        @Bean("/hello")
        public Controller controller1()
        {
            return (request, response) ->
            {
                response.getWriter().print("hello");
                return null;
            };
        }
    }

    @Configuration
    static class Config
    {
        @Bean
        public Bean1 bean1()
        {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2(Bean1 bean1)
        {
            Bean2 bean2 = new Bean2();
            bean2.setBean1(bean1);
            return bean2;
        }
    }

    static class Bean1
    {
    }

    static class Bean2
    {
        private Bean1 bean1;

        public void setBean1(Bean1 bean1)
        {
            this.bean1 = bean1;
        }

        public Bean1 getBean1()
        {
            return bean1;
        }
    }
}
