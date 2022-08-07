package com.eric.a06;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyConfig2 implements InitializingBean, ApplicationContextAware
{
    @Override
    public void afterPropertiesSet() throws Exception
    {
        log.debug("初始化");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        log.debug("注入 ApplicationContext");
    }

    /**
     * 添加Bean工厂后处理器
     */
    @Bean
    public BeanFactoryPostProcessor processor2()
    {
        return beanFactory -> log.debug("执行 processor2");
    }
}
