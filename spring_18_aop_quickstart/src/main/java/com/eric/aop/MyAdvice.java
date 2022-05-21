package com.eric.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description : AOP中的核心概念
 *
 * @author Eric L SHU
 */
@Component
@Aspect
public class MyAdvice
{
    /**
     * 设置切入点，要求配置在方法上方
     */
    @Pointcut("execution(void com.eric.dao.BookDao.update())")
    private void pointcut()
    {
    }

    /**
     * 设置在切入点pointcut()的前面运行当前操作（前置通知）
     */
    @Before("pointcut()")
    public void method()
    {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    }
}
