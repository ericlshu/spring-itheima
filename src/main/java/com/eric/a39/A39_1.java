package com.eric.a39;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Method;
import java.util.Set;

@Slf4j
@Configuration
public class A39_1
{
    public static void main(String[] args) throws Exception
    {
        log.debug("1. 演示获取 BeanDefinition 源");
        SpringApplication spring = new SpringApplication(A39_1.class);
        spring.setSources(Set.of("classpath:b01.xml"));

        log.debug("2. 演示推断应用类型");
        Method deduceFromClasspath = WebApplicationType.class.getDeclaredMethod("deduceFromClasspath");
        deduceFromClasspath.setAccessible(true);
        log.debug("\t应用类型为:" + deduceFromClasspath.invoke(null));

        log.debug("3. 演示 ApplicationContext 初始化器");
        spring.addInitializers(applicationContext ->
        {
            if (applicationContext instanceof GenericApplicationContext gac)
            {
                gac.registerBean("bean3", Bean3.class);
            }
        });

        log.debug("4. 演示监听器与事件");
        spring.addListeners(event -> log.info("\t事件为:" + event.getClass()));

        log.debug("5. 演示主类推断");
        Method deduceMainApplicationClass = SpringApplication.class.getDeclaredMethod("deduceMainApplicationClass");
        deduceMainApplicationClass.setAccessible(true);
        log.debug("\t主类是：" + deduceMainApplicationClass.invoke(spring));

        ConfigurableApplicationContext context = spring.run(args);

        // 创建 ApplicationContext
        // 调用初始化器 对 ApplicationContext 做扩展
        // ApplicationContext.refresh
        for (String name : context.getBeanDefinitionNames())
        {
            log.debug("name: " + name + " 来源：" + context.getBeanFactory().getBeanDefinition(name).getResourceDescription());
        }

        context.close();

        /*
         * a. SpringApplication 构造方法中所做的操作
         * -> 1. 可以有多种源用来加载 bean 定义
         * -> 2. 应用类型推断
         * -> 3. 容器初始化器
         * -> 4. 演示启动各阶段事件
         * -> 5. 演示主类推断
         */
    }

    static class Bean1
    {
    }

    static class Bean2
    {
    }

    static class Bean3
    {
    }

    @Bean
    public Bean2 bean2()
    {
        return new Bean2();
    }

    @Bean
    public TomcatServletWebServerFactory servletWebServerFactory()
    {
        return new TomcatServletWebServerFactory();
    }
}
