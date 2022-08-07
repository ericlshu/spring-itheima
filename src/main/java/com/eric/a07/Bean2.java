package com.eric.a07;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

@Slf4j
public class Bean2 implements DisposableBean
{
    @PreDestroy
    public void destroy1()
    {
        log.debug("销毁1 - 扩展功能 - @PreDestroy");
    }

    @Override
    public void destroy() throws Exception
    {
        log.debug("销毁2 - 接口方式 - destroy");
    }

    public void destroy3()
    {
        log.debug("销毁3 - @Bean - destroyMethod");
    }
}
