package org.springframework.aop.framework.autoproxy;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

/*
 * a. 自动代理后处理器 AnnotationAwareAspectJAutoProxyCreator 会帮我们创建代理
 * b. 通常代理创建的活在原始对象初始化后执行, 但碰到循环依赖会提前至依赖注入之前执行
 * c. 高级的 @Aspect 切面会转换为低级的 Advisor 切面, 理解原理, 大道至简
 */
@Slf4j
public class A17
{
    public static void main(String[] args)
    {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("aspect1", Aspect1.class);
        context.registerBean("config", Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);

        context.refresh();
        for (String name : context.getBeanDefinitionNames())
        {
            log.debug("bean name {}", name);
        }

        log.info("**********************************************");

        /*
         * 第一个重要方法 findEligibleAdvisors 找到有【资格】的 Advisors
         * -> a. 有【资格】的 Advisor 一部分是低级的, 可以由自己编写, 如下例中的 advisor3
         * -> b. 有【资格】的 Advisor 另一部分是高级的, 由本章的主角解析 @Aspect 后获得
         */
        AnnotationAwareAspectJAutoProxyCreator creator = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
        List<Advisor> advisors = creator.findEligibleAdvisors(Target1.class, "target1");
        for (Advisor advisor : advisors)
        {
            log.debug("bean advisor {}", advisor);
        }

        log.info("**********************************************");

        /*
         * 第二个重要方法 wrapIfNecessary
         * -> a. 它内部调用 findEligibleAdvisors, 只要返回集合不空, 则表示需要创建代理
         */
        Target1 target1 = (Target1) creator.wrapIfNecessary(new Target1(), "target1", "target1");
        log.debug("target1 class {}", target1.getClass());

        Target2 target2 = (Target2) creator.wrapIfNecessary(new Target2(), "target2", "target2");
        log.debug("target2 class {}", target2.getClass());

        target1.foo();
        target2.bar();
    }

    static class Target1
    {
        public void foo()
        {
            log.debug("target1 foo");
        }
    }

    static class Target2
    {
        public void bar()
        {
            log.debug("target2 bar");
        }
    }

    /**
     * 高级切面类
     */
    @Aspect
    // @Order(1)
    static class Aspect1
    {
        @Before("execution(* foo())")
        public void before()
        {
            log.info("aspect1 before...");
        }

        @After("execution(* foo())")
        public void after()
        {
            log.info("aspect1 after...");
        }
    }

    /**
     * 低级切面
     */
    @Configuration
    static class Config
    {
        @Bean
        public Advisor advisor3(MethodInterceptor advice3)
        {
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut, advice3);
        }

        @Bean
        public MethodInterceptor advice3()
        {
            return invocation ->
            {
                log.info("advice3 before...");
                Object result = invocation.proceed();
                log.info("advice3 after...");
                return result;
            };
        }
    }
}
