package com.eric.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 13:26
 */
public class MyAspect {

    public static final Logger LOGGER = LogManager.getLogger();

    public void before()
    {
        LOGGER.debug("before ...");
    }

    public void after()
    {
        LOGGER.debug("after ...");
    }

    /**
     * 环绕通知
     *
     * @param joinPoint 正在执行的连接点 即切点对象
     */
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        LOGGER.debug("before around ...");
        Object proceed = joinPoint.proceed();
        LOGGER.debug("after around ...");
        return proceed;
    }

    public void afterThrowing()
    {
        LOGGER.debug("after throwing ...");
    }

    public void afterReturning()
    {
        LOGGER.debug("after returning ...");
    }

}
