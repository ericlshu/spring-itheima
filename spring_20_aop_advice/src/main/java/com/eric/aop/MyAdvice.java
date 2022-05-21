package com.eric.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Description : AOP通知类型
 *
 * <p>
 * -> 环绕通知注意事项
 * -->> 环绕通知必须依赖形参ProceedingJoinPoint才能实现对原始方法的调用，进而实现原始方法调用前后同时添加通知
 * -->> 通知中如果未使用ProceedingJoinPoint对原始方法进行调用将跳过原始方法的执行
 * -->> 对原始方法的调用可以不接收返回值，通知方法设置成void即可，如果接收返回值，最好设定为Object类型
 * -->> 原始方法的返回值如果是void类型，通知方法的返回值类型可以设置成void,也可以设置成Object
 * -->> 由于无法预知原始方法运行后是否会抛出异常，因此环绕通知方法必须要处理Throwable异常
 *
 * @author Eric L SHU
 */
@Component
@Aspect
public class MyAdvice
{
    @Pointcut("execution(void com.eric.dao.BookDao.update())")
    private void updatePointCut()
    {
    }

    @Pointcut("execution(int com.eric.dao.BookDao.select())")
    private void selectPointCut()
    {
    }

    /**
     * 前置通知，在原始方法运行之前执行
     */
    @Before("selectPointCut()")
    public void before()
    {
        System.out.println("2. before advice ...");
    }

    /**
     * 后置通知，在原始方法运行之后执行
     * 后置通知是不管原始方法有没有抛出异常都会被执行。
     */
    @After("selectPointCut()")
    public void after()
    {
        System.out.println("4. after advice ...");
    }

    /**
     * 环绕通知，在原始方法运行的前后执行
     */
    @Around("updatePointCut()")
    public Object aroundUpdate(ProceedingJoinPoint joinPoint) throws Throwable
    {
        System.out.println("around before advice ...");
        // 表示对原始操作的调用
        Object result = joinPoint.proceed();
        System.out.println("around after advice ...");
        return result;
    }

    @Around("selectPointCut()")
    public Object aroundSelect(ProceedingJoinPoint joinPoint) throws Throwable
    {
        System.out.println("1. around before advice ...");
        // 表示对原始操作的调用
        Object result = joinPoint.proceed();
        System.out.println("5. around after advice ...");
        return result;
    }

    /**
     * 返回后通知，在原始方法执行完毕后运行，且原始方法执行过程中未出现异常现象
     * 返回后通知是需要在原始方法`select`正常执行后才会被执行，如果`select()`方法执行的过程中出现了异常，那么返回后通知是不会被执行。
     */
    @AfterReturning("selectPointCut()")
    public void afterReturning()
    {
        System.out.println("3. afterReturning advice ...");
    }

    /**
     * 抛出异常后通知，在原始方法执行过程中出现异常后运行
     */
    @AfterThrowing("selectPointCut()")
    public void afterThrowing()
    {
        System.out.println("afterThrowing advice ...");
    }
}
