package com.eric.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Description : AOP中的核心概念
 * <p>
 * -> 连接点(JoinPoint)：程序执行过程中的任意位置，粒度为执行方法、抛出异常、设置变量等
 * -->> 在SpringAOP中，理解为方法的执行
 * -> 切入点(Pointcut):匹配连接点的式子
 * -->> 在SpringAOP中，一个切入点可以描述一个具体方法，也可也匹配多个方法
 * --->>> 一个具体的方法:如com.eric.dao包下的BookDao接口中的无形参无返回值的save方法
 * --->>> 匹配多个方法:所有的save方法，所有的get开头的方法，所有以Dao结尾的接口中的任意方法，所有带有一个参数的方法
 * -->> 连接点范围要比切入点范围大，是切入点的方法也一定是连接点，但是是连接点的方法就不一定要被增强，所以可能不是切入点。
 * -> 通知(Advice):在切入点处执行的操作，也就是共性功能
 * -->> 在SpringAOP中，功能最终以方法的形式呈现
 * -> 通知类：定义通知的类
 * -> 切面(Aspect):描述通知与切入点的对应关系。
 *
 * @author Eric L SHU
 * @date 2022-05-21 12:20
 * @since jdk-11.0.14
 */
@Component
@Aspect
public class MyAdvice
{
    @Pointcut("execution(* com.eric.dao.BookDao.*d*(..))")
    private void pointcut()
    {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable
    {
        System.out.println("------------------------------");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++)
        {
            //调用原始操作
            joinPoint.proceed();
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("执行万次消耗时间：" + totalTime + "ms");
        return null;
    }
}
