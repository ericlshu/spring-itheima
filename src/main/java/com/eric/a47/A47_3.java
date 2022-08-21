package com.eric.a47;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 10. 有 @Primary 标注的 @Component 或 @Bean 的处理
 * 11. 与成员变量名或方法参数名同名 bean 的处理
 */
@Slf4j
@Configuration
@SuppressWarnings("all")
public class A47_3
{
    public static void main(String[] args) throws NoSuchFieldException
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A47_3.class);
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        testPrimary(beanFactory);
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        testDefault(beanFactory);
    }

    private static void testDefault(DefaultListableBeanFactory beanFactory) throws NoSuchFieldException
    {
        DependencyDescriptor dd = new DependencyDescriptor(Target2.class.getDeclaredField("service3"), false);
        Class<?> type = dd.getDependencyType();
        for (String name : BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, type))
        {
            log.debug("Bean Name : {}", name);
            if (name.equals(dd.getDependencyName()))
            {
                log.debug("└─── Item : {}", dd.resolveCandidate(name, type, beanFactory));
            }
        }
    }

    private static void testPrimary(DefaultListableBeanFactory beanFactory) throws NoSuchFieldException
    {
        DependencyDescriptor dd = new DependencyDescriptor(Target1.class.getDeclaredField("service"), false);
        Class<?> type = dd.getDependencyType();
        for (String name : BeanFactoryUtils.beanNamesForTypeIncludingAncestors(beanFactory, type))
        {
            log.debug("Bean Name : {}", name);
            if (beanFactory.getMergedBeanDefinition(name).isPrimary())
            {
                log.debug("└─── Item : {}", dd.resolveCandidate(name, type, beanFactory));
            }
        }
    }

    static class Target1
    {
        @Autowired
        private Service service;
    }

    static class Target2
    {
        @Autowired
        private Service service3;
    }

    interface Service
    {
    }

    @Component("service1")
    static class Service1 implements Service
    {
    }

    @Component("service2")
    @Primary
    static class Service2 implements Service
    {
    }

    @Component("service3")
    static class Service3 implements Service
    {
    }
}
