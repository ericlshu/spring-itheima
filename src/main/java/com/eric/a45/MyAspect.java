package com.eric.a45;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class MyAspect
{
    // 故意对所有方法增强
    @Before("execution(* com.eric.a45.Bean1.*(..))")
    public void before()
    {
        log.info("before");
    }
}
