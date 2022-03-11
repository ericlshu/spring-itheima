package com.eric.anno;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @ Aspect 标注当前类是切面类
 * @date 2022-03-11 13:26
 */
@Component("myAspect")
@Aspect
public class MyAspect {

    public static final Logger LOGGER = LogManager.getLogger();

    @Before("execution(* com.eric.anno.Target.*())")
    public void before()
    {
        LOGGER.debug("before ...");
    }

    @After("execution(* com.eric.anno.Target.*())")
    public void after()
    {
        LOGGER.debug("after ...");
    }

    /**
     * 环绕通知
     *
     * @param joinPoint 正在执行的连接点 即切点对象
     */
    @Around("myPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        LOGGER.debug("before around ...");
        System.out.println("joinPoint = " + joinPoint);
        Object proceed = joinPoint.proceed();
        LOGGER.debug("after around ...");
        return proceed;
    }

    @AfterThrowing("MyAspect.myPoint()")
    public void afterThrowing()
    {
        LOGGER.debug("after throwing ...");
    }

    @AfterReturning("MyAspect.myPoint()")
    public void afterReturning()
    {
        LOGGER.debug("after returning ...");
    }

    @Pointcut("execution(* com.eric.aop.Target.*(..))")
    public void myPoint()
    {
    }
}
