package org.springframework.aop.framework.autoproxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.*;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/*
 * 高级切面转低级切面类
 * @Before 前置通知会被转换为下面原始的 AspectJMethodBeforeAdvice 形式, 该对象包含了如下信息
 * -> a. 通知代码从哪儿来
 * -> b. 切点是什么(这里为啥要切点, 后面解释)
 * -> c. 通知对象如何创建, 本例共用同一个 Aspect 对象
 * 类似的通知还有
 * -> 1. AspectJAroundAdvice (环绕通知)
 * -> 2. AspectJAfterReturningAdvice
 * -> 3. AspectJAfterThrowingAdvice
 * -> 4. AspectJAfterAdvice (环绕通知)
 */
@Slf4j
@SuppressWarnings("all")
public class A17_2
{
    public static void main(String[] args) throws Throwable
    {
        // 切面对象的实例工厂
        AspectInstanceFactory factory = new SingletonAspectInstanceFactory(new Aspect());
        List<Advisor> list = new ArrayList<>();
        String expression;
        AspectJExpressionPointcut pointcut;
        AbstractAspectJAdvice advice = null;
        for (Method method : Aspect.class.getDeclaredMethods())
        {
            pointcut = new AspectJExpressionPointcut();
            // 解析切点表达式
            if (method.isAnnotationPresent(Before.class))
            {
                expression = method.getAnnotation(Before.class).value();
                pointcut.setExpression(expression);
                advice = new AspectJMethodBeforeAdvice(method, pointcut, factory);
            }
            else if (method.isAnnotationPresent(After.class))
            {
                expression = method.getAnnotation(After.class).value();
                pointcut.setExpression(expression);
                advice = new AspectJAfterAdvice(method, pointcut, factory);
            }
            else if (method.isAnnotationPresent(Around.class))
            {
                expression = method.getAnnotation(Around.class).value();
                pointcut.setExpression(expression);
                advice = new AspectJAroundAdvice(method, pointcut, factory);
            }
            else if (method.isAnnotationPresent(AfterReturning.class))
            {
                expression = method.getAnnotation(AfterReturning.class).value();
                pointcut.setExpression(expression);
                advice = new AspectJAfterReturningAdvice(method, pointcut, factory);
            }
            else if (method.isAnnotationPresent(AfterThrowing.class))
            {
                expression = method.getAnnotation(AfterThrowing.class).value();
                pointcut.setExpression(expression);
                advice = new AspectJAfterThrowingAdvice(method, pointcut, factory);
            }

            if (advice != null)
            {
                list.add(new DefaultPointcutAdvisor(pointcut, advice));
            }

            advice = null;
        }
        for (Advisor item : list)
        {
            log.debug(item.toString());
        }
    }

    private static AspectJExpressionPointcut getPointcut(String expression)
    {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    static class Aspect
    {
        @Before("execution(* foo())")
        public void before1()
        {
            log.info("before1");
        }

        @Before("execution(* foo())")
        public void before2()
        {
            log.info("before2");
        }

        @After("execution(* foo())")
        public void after()
        {
            log.info("after");
        }

        @AfterReturning("execution(* foo())")
        public void afterReturning()
        {
            log.info("afterReturning");
        }

        @AfterThrowing("execution(* foo())")
        public void afterThrowing()
        {
            log.info("afterThrowing");
        }

        @Around("execution(* foo())")
        public Object around(ProceedingJoinPoint pjp) throws Throwable
        {
            try
            {
                log.info("around...before");
                return pjp.proceed();
            }
            finally
            {
                log.info("around...after");
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
