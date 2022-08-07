package com.eric.a10.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect // ⬅️注意此切面并未被 Spring 管理
public class MyAspect
{
    @Before("execution(* com.eric.a10.service.MyService.*())")
    public void before()
    {
        log.debug("before()");
    }
}
