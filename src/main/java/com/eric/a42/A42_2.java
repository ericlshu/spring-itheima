package com.eric.a42;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Slf4j
public class A42_2
{
    public static void main(String[] args) throws IOException
    {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();

        for (String name : context.getBeanDefinitionNames())
        {
            log.debug("Bean in Context : {}", name);
        }
    }

    @Configuration // 本项目的配置类
    @Import(MyImportSelector.class)
    static class Config
    {
    }

    static class MyImportSelector implements DeferredImportSelector
    {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata)
        {
            return new String[]{AutoConfiguration1.class.getName(), AutoConfiguration2.class.getName()};
        }
    }

    static class MyCondition implements Condition
    {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
        {
            Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnClass.class.getName());
            String className = attributes.get("className").toString();
            boolean exists = (boolean) attributes.get("exists");
            boolean present = ClassUtils.isPresent(className, null);
            return exists == present;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.TYPE})
    @Conditional(MyCondition.class)
    @interface ConditionalOnClass
    {
        /**
         * @return true 判断存在 false 判断不存在
         */
        boolean exists();

        /**
         * @return 要判断的类名
         */
        String className();
    }

    @Configuration // 第三方的配置类
    @ConditionalOnClass(className = "com.alibaba.druid.pool.DruidDataSource", exists = false)
    static class AutoConfiguration1
    {
        @Bean
        public Bean1 bean1()
        {
            return new Bean1();
        }
    }

    @Configuration // 第三方的配置类
    @ConditionalOnClass(className = "com.alibaba.druid.pool.DruidDataSource", exists = true)
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
