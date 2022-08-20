package com.eric.a41;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.io.IOException;

@Slf4j
public class A41_2
{
    public static void main(String[] args) throws IOException
    {
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext();

        // 设置数据源连接信息，使Spring可以根据连接信息推测数据库类型，从而加载相关自动配置
        StandardEnvironment env = new StandardEnvironment();
        env.getPropertySources().addLast(new SimpleCommandLinePropertySource(
                "--spring.datasource.url=jdbc:mysql://192.168.3.33:3306/spring",
                "--spring.datasource.username=eric",
                "--spring.datasource.password=1234"
        ));
        context.setEnvironment(env);

        context.registerBean("config", Config.class);
        context.refresh();

        for (String name : context.getBeanDefinitionNames())
        {
            String resourceDescription = context.getBeanDefinition(name).getResourceDescription();
            if (resourceDescription != null)
                log.debug(name + " <from:> " + resourceDescription);
        }
        context.close();
    }

    @Configuration // 本项目的配置类
    // @Import(MyImportSelector.class)
    @EnableAutoConfiguration
    static class Config
    {
        @Bean
        public TomcatServletWebServerFactory tomcatServletWebServerFactory()
        {
            return new TomcatServletWebServerFactory();
        }
    }

    static class MyImportSelector implements DeferredImportSelector
    {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata)
        {
            return SpringFactoriesLoader.loadFactoryNames(MyImportSelector.class, null).toArray(new String[0]);
        }
    }

    @Configuration // 第三方的配置类
    static class AutoConfiguration1
    {
        @Bean
        public Bean1 bean1()
        {
            return new Bean1();
        }
    }

    @Configuration // 第三方的配置类
    static class AutoConfiguration2
    {
        @Bean
        public Bean2 bean2()
        {
            return new Bean2();
        }
    }

    static class Bean1
    {
    }

    static class Bean2
    {
    }
}
