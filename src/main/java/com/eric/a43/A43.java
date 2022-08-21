package com.eric.a43;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 一个在 Spring 发展阶段中重要, 但目前已经很鸡肋的接口 FactoryBean 的使用要点
 * 说它鸡肋有两点:
 * 1. 它的作用是用制造创建过程较为复杂的产品, 如 SqlSessionFactory, 但 @Bean 已具备等价功能
 * 2. 使用上较为古怪, 一不留神就会用错
 * -> a. 被 FactoryBean 创建的产品
 * ---> 会认为创建、依赖注入、Aware 接口回调、前初始化这些都是 FactoryBean 的职责, 这些流程都不会走
 * ---> 唯有后初始化的流程会走, 也就是产品可以被代理增强
 * ---> 单例的产品不会存储于 BeanFactory 的 singletonObjects 成员中, 而是另一个 factoryBeanObjectCache 成员中
 * -> b. 按名字去获取时, 拿到的是产品对象, 名字前面加 & 获取的是工厂对象
 * 但目前此接口的实现仍被大量使用, 想被全面废弃很难
 */
@Slf4j
@ComponentScan
public class A43
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A43.class);

        log.info(String.valueOf(context.getBean("bean1")));
        log.info(String.valueOf(context.getBean("bean1")));
        log.info(String.valueOf(context.getBean("bean1")));

        log.info(String.valueOf(context.getBean(Bean1.class)));
        log.info(String.valueOf(context.getBean(Bean1FactoryBean.class)));
        log.info(String.valueOf(context.getBean("&bean1")));

        context.close();
    }
}
