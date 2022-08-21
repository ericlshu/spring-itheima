package com.eric.a46;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

// 本章节作为第四讲的延续
@Slf4j
@Configuration
@SuppressWarnings("all")
public class A46
{
    public static void main(String[] args) throws Exception
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A46.class);
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();

        ContextAnnotationAutowireCandidateResolver resolver = new ContextAnnotationAutowireCandidateResolver();
        resolver.setBeanFactory(beanFactory);

        test1(context, resolver, Bean1.class.getDeclaredField("home"));
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        test2(context, resolver, Bean1.class.getDeclaredField("age"));
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        test3(context, resolver, Bean2.class.getDeclaredField("bean3"));
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        test3(context, resolver, Bean4.class.getDeclaredField("value"));
    }

    private static void test3(AnnotationConfigApplicationContext context, ContextAnnotationAutowireCandidateResolver resolver, Field field)
    {
        DependencyDescriptor dd1 = new DependencyDescriptor(field, false);
        // 获取 @Value 的内容
        String value = resolver.getSuggestedValue(dd1).toString();
        log.debug(value);

        // 解析 ${}
        value = context.getEnvironment().resolvePlaceholders(value);
        log.debug(value);
        log.debug(String.valueOf(value.getClass()));

        // 解析 #{} @bean3
        Object bean3 = context.getBeanFactory().getBeanExpressionResolver().evaluate(value, new BeanExpressionContext(context.getBeanFactory(), null));

        // 类型转换
        Object result = context.getBeanFactory().getTypeConverter().convertIfNecessary(bean3, dd1.getDependencyType());
        log.debug(String.valueOf(result));
    }

    private static void test2(AnnotationConfigApplicationContext context, ContextAnnotationAutowireCandidateResolver resolver, Field field)
    {
        DependencyDescriptor dd1 = new DependencyDescriptor(field, false);
        // 获取 @Value 的内容
        String value = resolver.getSuggestedValue(dd1).toString();
        log.debug(value);

        // 解析 ${}
        value = context.getEnvironment().resolvePlaceholders(value);
        log.debug(value);
        log.debug(String.valueOf(value.getClass()));
        Object age = context.getBeanFactory().getTypeConverter().convertIfNecessary(value, dd1.getDependencyType());
        log.debug(String.valueOf(age.getClass()));
    }

    private static void test1(AnnotationConfigApplicationContext context, ContextAnnotationAutowireCandidateResolver resolver, Field field)
    {
        DependencyDescriptor dd1 = new DependencyDescriptor(field, false);
        // 获取 @Value 的内容
        String value = resolver.getSuggestedValue(dd1).toString();
        log.debug(value);

        // 解析 ${}
        value = context.getEnvironment().resolvePlaceholders(value);
        log.debug(value);
    }

    public class Bean1
    {
        @Value("${JAVA_HOME}")
        private String home;
        @Value("18")
        private int age;
    }

    public class Bean2
    {
        @Value("#{@bean3}") // SpringEL       #{SpEL}
        private Bean3 bean3;
    }

    @Component("bean3")
    public class Bean3
    {
    }

    static class Bean4
    {
        @Value("#{'hello, ' + '${JAVA_HOME}'}")
        private String value;
    }
}
