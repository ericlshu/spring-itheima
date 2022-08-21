package com.eric.a45;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class Bean1
{
    protected Bean2 bean2;

    protected boolean initialized;

    @Autowired
    public void setBean2(Bean2 bean2)
    {
        log.debug("setBean2(Bean2 bean2)");
        this.bean2 = bean2;
    }

    @PostConstruct
    public void init()
    {
        log.debug("init");
        initialized = true;
    }

    public Bean2 getBean2()
    {
        log.debug("getBean2()");
        return bean2;
    }

    public boolean isInitialized()
    {
        log.debug("isInitialized()");
        return initialized;
    }

    public void m1()
    {
        log.info("m1() 成员方法");
    }

    final public void m2()
    {
        log.info("m2() final 方法");
    }

    static public void m3()
    {
        log.info("m3() static 方法");
    }

    private void m4()
    {
        log.info("m4() private 方法");
    }

}
