package org.springframework.aop.framework.autoproxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

import java.lang.reflect.Field;
import java.util.List;

/*
 * 动态通知调用-带参数绑定的通知方法调用
 * -> a. 有参数绑定的通知调用时还需要切点，对参数进行匹配及绑定
 * -> b. 复杂程度高, 性能比无参数绑定的通知调用低
 */
@Slf4j
public class A19
{
    @Aspect
    static class MyAspect
    {
        // 静态通知调用，不带参数绑定，执行时不需要切点
        @Before("execution(* foo(..))")
        public void before1()
        {
            log.info("before1");
        }

        // 动态通知调用，需要参数绑定，执行时还需要切点对象
        @Before("execution(* foo(..)) && args(x)")
        public void before2(int x)
        {
            log.info("before2({})", x);
        }
    }

    static class Target
    {
        public void foo(int x)
        {
            log.debug("target foo({})", x);
        }
    }

    @Configuration
    static class MyConfig
    {
        @Bean
        AnnotationAwareAspectJAutoProxyCreator proxyCreator()
        {
            return new AnnotationAwareAspectJAutoProxyCreator();
        }

        @Bean
        public MyAspect myAspect()
        {
            return new MyAspect();
        }
    }

    public static void main(String[] args) throws Throwable
    {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(MyConfig.class);
        context.refresh();

        AnnotationAwareAspectJAutoProxyCreator creator = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class);
        List<Advisor> list = creator.findEligibleAdvisors(Target.class, "target");

        Target target = new Target();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisors(list);
        Object proxy = factory.getProxy();

        List<Object> interceptorList =
                factory.getInterceptorsAndDynamicInterceptionAdvice(Target.class.getMethod("foo", int.class), Target.class);
        for (Object methodInterceptor : interceptorList)
        {
            showDetail(methodInterceptor);
        }

        ReflectiveMethodInvocation invocation = new ReflectiveMethodInvocation(
                proxy, target, Target.class.getMethod("foo", int.class), new Object[]{100}, Target.class, interceptorList
        )
        {
        };

        invocation.proceed();
    }

    public static void showDetail(Object o)
    {
        try
        {
            Class<?> clazz = Class.forName("org.springframework.aop.framework.InterceptorAndDynamicMethodMatcher");
            if (clazz.isInstance(o))
            {
                Field methodMatcher = clazz.getDeclaredField("methodMatcher");
                methodMatcher.setAccessible(true);
                Field methodInterceptor = clazz.getDeclaredField("interceptor");
                methodInterceptor.setAccessible(true);
                log.debug("环绕通知和切点：" + o);
                log.info("-->> 切点为：" + methodMatcher.get(o));
                log.info("-->> 通知为：" + methodInterceptor.get(o));
            }
            else
            {
                log.debug("普通环绕通知：" + o);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
