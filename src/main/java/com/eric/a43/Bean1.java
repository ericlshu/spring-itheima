package com.eric.a43;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Slf4j
public class Bean1 implements BeanFactoryAware
{
    private Bean2 bean2;

    @Autowired
    public void setBean2(Bean2 bean2)
    {
        log.debug("setBean2({})", bean2);
        this.bean2 = bean2;
    }

    public Bean2 getBean2()
    {
        return bean2;
    }

    @PostConstruct
    public void init()
    {
        log.debug("init");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException
    {
        log.debug("setBeanFactory({})", beanFactory);
    }
}
