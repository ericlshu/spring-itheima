package com.eric.a04;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

/*
 * bean 后处理器的作用
 * -> a. @Autowired 等注解的解析属于 bean 生命周期阶段(依赖注入, 初始化)的扩展功能
 * -> b. 这些扩展功能由 bean 后处理器来完成
 */
@Slf4j
public class A04
{
    public static void main(String[] args)
    {
        // ⬇️GenericApplicationContext 是一个【干净】的容器
        GenericApplicationContext context = new GenericApplicationContext();

        // ⬇️用原始方法注册三个 bean
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);
        context.registerBean("bean4", Bean4.class);

        // 使用ContextAnnotationAutowireCandidateResolver获取@Value中的值
        context.getDefaultListableBeanFactory()
                .setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // 解析 @Autowired @Value 注解
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        // 解析 @Resource @PostConstruct @PreDestroy 注解
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        // 解析 @ConfigurationProperties 注解
        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());

        // ⬇️初始化容器, 执行beanFactory后处理器, 添加bean后处理器, 初始化所有单例
        context.refresh();

        log.debug(context.getBean(Bean4.class).toString());

        // ⬇️销毁容器
        context.close();
    }
}
