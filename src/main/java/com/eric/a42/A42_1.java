package com.eric.a42;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.io.IOException;

@Slf4j
public class A42_1
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

    @Configuration // 第三方的配置类
    @Conditional(MyCondition1.class)
    static class AutoConfiguration1
    {
        @Bean
        public Bean1 bean1()
        {
            return new Bean1();
        }
    }

    @Configuration // 第三方的配置类
    @Conditional(MyCondition2.class)
    static class AutoConfiguration2
    {
        @Bean
        public Bean2 bean2()
        {
            return new Bean2();
        }
    }

    static class MyCondition1 implements Condition
    { // 存在 Druid 依赖
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
        {
            return ClassUtils.isPresent("com.alibaba.druid.pool.DruidDataSource", null);
        }
    }

    static class MyCondition2 implements Condition
    { // 不存在 Druid 依赖
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata)
        {
            return !ClassUtils.isPresent("com.alibaba.druid.pool.DruidDataSource", null);
        }
    }

    static class Bean1
    {
    }

    static class Bean2
    {
    }
}
