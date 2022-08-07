package com.eric.a07;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

@Slf4j
public class Bean1 implements InitializingBean
{
    @PostConstruct
    public void init1()
    {
        log.debug("初始化1 - 扩展功能 - @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        log.debug("初始化2 - 接口方式 - afterPropertiesSet");
    }

    public void init3()
    {
        log.debug("初始化3 - BeanDefinition - initMethod");
    }
}
