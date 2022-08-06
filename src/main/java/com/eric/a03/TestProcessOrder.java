package com.eric.a03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

/*
 * bean 后处理的的排序
 * -> 1. 实现了 PriorityOrdered 接口的优先级最高
 * -> 2. 实现了 Ordered 接口与加了 @Order 注解的平级, 按数字升序
 * -> 3. 其它的排在最后
 */
public class TestProcessOrder
{
    public static void main(String[] args)
    {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        List<BeanPostProcessor> list = new ArrayList<>(List.of(new P1(), new P2(), new P3(), new P4(), new P5()));
        list.sort(beanFactory.getDependencyComparator());

        list.forEach(processor ->
                processor.postProcessBeforeInitialization(new Object(), "")
        );
    }

    @Slf4j
    @Order(1)
    static class P1 implements BeanPostProcessor
    {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
        {
            log.debug("postProcessBeforeInitialization @Order(1)");
            return bean;
        }
    }

    @Slf4j
    @Order(2)
    static class P2 implements BeanPostProcessor
    {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
        {
            log.debug("postProcessBeforeInitialization @Order(2)");
            return bean;
        }

    }

    @Slf4j
    static class P3 implements BeanPostProcessor, PriorityOrdered
    {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
        {
            log.debug("postProcessBeforeInitialization PriorityOrdered");
            return bean;
        }

        @Override
        public int getOrder()
        {
            return 100;
        }
    }

    @Slf4j
    static class P4 implements BeanPostProcessor, Ordered
    {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
        {
            log.debug("postProcessBeforeInitialization Ordered");
            return bean;
        }

        @Override
        public int getOrder()
        {
            return 0;
        }
    }

    @Slf4j
    static class P5 implements BeanPostProcessor
    {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException
        {
            log.debug("postProcessBeforeInitialization");
            return bean;
        }
    }
}
