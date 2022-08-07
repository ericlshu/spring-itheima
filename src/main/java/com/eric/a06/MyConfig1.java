package com.eric.a06;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class MyConfig1
{
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        log.debug("注入 ApplicationContext");
    }

    @PostConstruct
    public void init()
    {
        log.debug("初始化");
    }

    /**
     * 添加Bean工厂后处理器
     */
    @Bean
    public BeanFactoryPostProcessor processor1()
    {
        return beanFactory -> log.debug("执行 processor1");
    }
}
