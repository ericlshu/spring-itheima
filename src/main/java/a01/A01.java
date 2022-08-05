package a01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Map;

/**
 * BeanFactory 与 ApplicationContext 的区别
 */
@SpringBootApplication
public class A01
{
    private static final Logger log = LoggerFactory.getLogger(A01.class);

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException
    {
        /*
         * 1. 到底什么是 BeanFactory
         * -> 它是 ApplicationContext 的父接口
         * -> 它才是 Spring 的核心容器, 主要的 ApplicationContext 实现都【组合】了它的功能
         */
        ConfigurableApplicationContext context = SpringApplication.run(A01.class, args);
        log.warn(context.toString());

        /*
         * 2. BeanFactory 能干点啥
         * -> 表面上只有 getBean
         * -> 实际上控制反转、基本的依赖注入、直至 Bean 的生命周期的各种功能, 都由它的实现类提供
         */
        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);
        // map.forEach((k, v) -> log.warn(k + " = " + v));
        map.entrySet().stream().filter(e -> e.getKey().startsWith("component"))
                .forEach(e -> log.warn(e.getKey() + "=" + e.getValue()));

        /*
         * 3. ApplicationContext 比 BeanFactory 多点啥
         * -> 国际化，资源通配符匹配，事件对象发布器，环境信息处理
         */
        log.debug(context.getMessage("hi", null, Locale.CHINA));
        log.debug(context.getMessage("hi", null, Locale.ENGLISH));
        log.debug(context.getMessage("hi", null, Locale.JAPANESE));

        Resource[] resources = context.getResources("classpath*:META-INF/spring.factories");
        for (Resource resource : resources)
        {
            log.info(resource.toString());
        }

        log.warn(context.getEnvironment().getProperty("java_home"));
        log.warn(context.getEnvironment().getProperty("server.port"));

        // context.publishEvent(new UserRegisteredEvent(context));
        context.getBean(Component1.class).register();

        /*
         * 4. 学到了什么
         * -> a. BeanFactory 与 ApplicationContext 并不仅仅是简单接口继承的关系, ApplicationContext 组合并扩展了 BeanFactory 的功能
         * -> b. 又新学一种代码之间解耦途径
         * 练习：完成用户注册与发送短信之间的解耦, 用事件方式、和 AOP 方式分别实现
         */
    }
}
