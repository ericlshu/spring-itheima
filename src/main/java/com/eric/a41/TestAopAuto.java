package com.eric.a41;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * AopAutoConfiguration, Spring Boot 是利用了自动配置类来简化了 aop 相关配置
 * -> AOP 自动配置类为 `org.springframework.boot.autoconfigure.aop.AopAutoConfiguration`
 * -> 可以通过 `spring.aop.auto=false` 禁用 aop 自动配置
 * -> AOP 自动配置的本质是通过 `@EnableAspectJAutoProxy` 来开启了自动代理，如果在引导类上自己添加了 `@EnableAspectJAutoProxy` 那么以自己添加的为准
 * -> @EnableAspectJAutoProxy 的本质是向容器中添加了 `AnnotationAwareAspectJAutoProxyCreator` 这个 bean 后处理器，它能够找到容器中所有切面，并为匹配切点的目标类创建代理，创建代理的工作一般是在 bean 的初始化阶段完成的
 */
@Slf4j
public class TestAopAuto
{
    public static void main(String[] args)
    {
        GenericApplicationContext context = new GenericApplicationContext();

        // 通过 `spring.aop.auto=false` 禁用 aop 自动配置，模拟AopAutoConfiguration中@ConditionalOnProperty条件
        StandardEnvironment env = new StandardEnvironment();
        env.getPropertySources().addLast(new SimpleCommandLinePropertySource("--spring.aop.auto=true"));
        context.setEnvironment(env);

        AnnotationConfigUtils.registerAnnotationConfigProcessors(context.getDefaultListableBeanFactory());
        context.registerBean(Config.class);
        context.refresh();
        for (String name : context.getBeanDefinitionNames())
        {
            log.debug("Bean in Context : {}", name);
        }

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        AnnotationAwareAspectJAutoProxyCreator creator = context.getBean(
                "org.springframework.aop.config.internalAutoProxyCreator", AnnotationAwareAspectJAutoProxyCreator.class);
        log.debug("isProxyTargetClass : {}",creator.isProxyTargetClass());
    }

    @Configuration
    @Import(MyImportSelector.class)
    static class Config
    {
    }

    static class MyImportSelector implements DeferredImportSelector
    {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata)
        {
            return new String[]{AopAutoConfiguration.class.getName()};
        }
    }
}
