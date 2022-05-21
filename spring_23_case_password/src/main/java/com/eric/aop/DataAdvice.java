package com.eric.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric SHU
 */
@Component
@Aspect
public class DataAdvice
{
    @Pointcut("execution(boolean com.eric.service.*Service.*(*,*))")
    private void servicePointCut()
    {
    }

    @Around("DataAdvice.servicePointCut()")
    public Object trimString(ProceedingJoinPoint pjp) throws Throwable
    {
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++)
        {
            if (String.class.equals(args[i].getClass()))
            {
                System.out.println("args[" + i + "] = " + args[i]);
                args[i] = args[i].toString().trim();
            }
        }
        return pjp.proceed(args);
    }
}
