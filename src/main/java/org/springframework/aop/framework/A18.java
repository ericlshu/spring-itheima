package org.springframework.aop.framework;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.*;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
 * 通知统一转换为环绕通知 MethodInterceptor
 *
 * 其实无论 ProxyFactory 基于哪种方式创建代理, 最后干活(调用 advice)的是一个 MethodInvocation 对象
 * -> a. 因为 advisor 有多个, 且一个套一个调用, 因此需要一个调用链对象, 即 MethodInvocation
 * -> b. MethodInvocation 要知道 advice 有哪些, 还要知道目标, 调用次序如下
 *   将 MethodInvocation 放入当前线程
 *       |-> before1 ----------------------------------- 从当前线程获取 MethodInvocation
 *       |                                             |
 *       |   |-> before2 --------------------          | 从当前线程获取 MethodInvocation
 *       |   |                              |          |
 *       |   |   |-> target ------ 目标   advice2    advice1
 *       |   |                              |          |
 *       |   |-> after2 ---------------------          |
 *       |                                             |
 *       |-> after1 ------------------------------------
 * -> c. 从上图看出, 环绕通知才适合作为 advice, 因此其他 before、afterReturning 都会被转换成环绕通知
 * -> d. 统一转换为环绕通知, 体现的是设计模式中的适配器模式
 * -->> 对外是为了方便使用要区分 before、afterReturning
 * -->> 对内统一都是环绕通知, 统一用 MethodInterceptor 表示
 *
 * 此步获取所有执行时需要的 advice (静态)
 * -> a. 即统一转换为 MethodInterceptor 环绕通知, 这体现在方法名中的 Interceptors 上
 * -> b. 适配如下
 * -->> MethodBeforeAdviceAdapter 将 @Before AspectJMethodBeforeAdvice 适配为 MethodBeforeAdviceInterceptor
 * -->> AfterReturningAdviceAdapter 将 @AfterReturning AspectJAfterReturningAdvice 适配为 AfterReturningAdviceInterceptor
 *
 * a. 无参数绑定的通知如何被调用
 * b. MethodInvocation 编程技巧: 拦截器、过滤器等等实现都与此类似
 * c. 适配器模式在 Spring 中的体现
 */
@Slf4j
@SuppressWarnings("all")
public class A18
{
    public static void main(String[] args) throws Throwable
    {
        // 1. 高级切面转低级切面类
        AspectInstanceFactory factory = new SingletonAspectInstanceFactory(new Aspect());
        List<Advisor> list = new ArrayList<>();
        AspectJExpressionPointcut pointcut;
        AbstractAspectJAdvice advice = null;
        for (Method method : Aspect.class.getDeclaredMethods())
        {
            pointcut = new AspectJExpressionPointcut();
            if (method.isAnnotationPresent(Before.class))
            {
                pointcut.setExpression(method.getAnnotation(Before.class).value());
                advice = new AspectJMethodBeforeAdvice(method, pointcut, factory);
            }
            else if (method.isAnnotationPresent(AfterReturning.class))
            {
                pointcut.setExpression(method.getAnnotation(AfterReturning.class).value());
                advice = new AspectJAfterReturningAdvice(method, pointcut, factory);
            }
            else if (method.isAnnotationPresent(Around.class))
            {
                pointcut.setExpression(method.getAnnotation(Around.class).value());
                advice = new AspectJAroundAdvice(method, pointcut, factory);
            }
            if (advice != null)
            {
                list.add(new DefaultPointcutAdvisor(pointcut, advice));
            }
            advice = null;
        }
        for (Advisor advisor : list)
        {
            log.debug("advisor : {}", advisor);
        }

        // 2. 通知统一转换为环绕通知 MethodInterceptor
        Target target = new Target();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        // 准备把 MethodInvocation 放入当前线程，相当于加了一个环绕通知
        proxyFactory.addAdvice(ExposeInvocationInterceptor.INSTANCE);
        proxyFactory.addAdvisors(list);

        List<Object> methodInterceptorList =
                proxyFactory.getInterceptorsAndDynamicInterceptionAdvice(Target.class.getMethod("foo"), Target.class);
        for (Object methodInterceptor : methodInterceptorList)
        {
            log.info("methodInterceptor : {}", methodInterceptor);
        }

        // 3. 创建并执行调用链 (环绕通知s + 目标)
        MethodInvocation methodInvocation = new ReflectiveMethodInvocation(
                null, target, Target.class.getMethod("foo"), new Object[0], Target.class, methodInterceptorList
        );
        methodInvocation.proceed();
    }

    static class Aspect
    {
        @Before("execution(* foo())")
        public void before1()
        {
            log.warn("before1");
        }

        @Before("execution(* foo())")
        public void before2()
        {
            log.warn("before2");
        }

        public void after()
        {
            log.warn("after");
        }

        @AfterReturning("execution(* foo())")
        public void afterReturning()
        {
            log.warn("afterReturning");
        }

        @AfterThrowing("execution(* foo())")
        public void afterThrowing(Exception e)
        {
            log.warn("afterThrowing " + e.getMessage());
        }

        @Around("execution(* foo())")
        public Object around(ProceedingJoinPoint pjp) throws Throwable
        {
            try
            {
                log.warn("around...before");
                return pjp.proceed();
            }
            finally
            {
                log.warn("around...after");
            }
        }
    }

    static class Target
    {
        public void foo()
        {
            log.debug("target foo");
        }
    }
}
