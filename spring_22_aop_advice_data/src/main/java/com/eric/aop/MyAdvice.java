package com.eric.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Description : AOP通知获取数据
 * JoinPoint            ：用于描述切入点的对象，必须配置成通知方法中的第一个参数，可用于获取原始方法调用的参数
 * ProceedingJoinPoint  ：专用于环绕通知，是JoinPoint子类，可以实现对原始方法的调用
 *
 * @author Eric SHU
 */
@Component
@Aspect
public class MyAdvice
{
    @Pointcut("execution(* com.eric.dao.BookDao.findName(..))")
    private void pointCut()
    {
    }

    // @Before("pointCut()")
    public void before(JoinPoint jp)
    {
        Object[] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("before advice ...");
    }

    // @After("pointCut()")
    public void after(JoinPoint jp)
    {
        Object[] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("after advice ...");
    }

    /**
     * 在环绕通知中对原始方法的参数进行拦截过滤，避免由于参数的问题导致程序无法正确运行，保证代码的健壮性。
     */
    // @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp)
    {
        Object[] args = pjp.getArgs();
        System.out.println(Arrays.toString(args));
        args[0] = 666;
        // 环绕通知获取异常
        try
        {
            return pjp.proceed(args);
        }
        catch (Throwable e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置返回后通知获取原始方法的返回值，要求returning属性值必须与方法形参名相同
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint jp, String result)
    {
        System.out.println("afterReturning advice ...");
        System.out.println("result = " + result);
    }

    /**
     * 设置抛出异常后通知获取原始方法运行时抛出的异常对象，要求throwing属性值必须与方法形参名相同
     */
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void afterThrowing(Throwable exception)
    {
        System.out.println("afterThrowing advice ...");
        System.out.println("exception = " + exception);
    }
}
