package com.eric.a48;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 事件监听器的两种方式之一
 * 1. 实现 ApplicationListener 接口
 * -> 根据接口泛型确定事件类型
 */
@Slf4j
@Configuration
@SuppressWarnings("all")
public class A48_1
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A48_1.class);
        context.getBean(MyService.class).doBusiness();
        context.close();
    }

    static class MyEvent extends ApplicationEvent
    {
        public MyEvent(Object source)
        {
            super(source);
        }
    }

    @Slf4j
    @Component
    static class MyService
    {
        @Autowired
        private ApplicationEventPublisher publisher; // applicationContext

        public void doBusiness()
        {
            log.debug("主线业务");

            // 主线业务完成后需要做一些支线业务，下面是问题代码
            // log.debug("发送短信");
            // log.debug("发送邮件");

            publisher.publishEvent(new MyEvent("MyService.doBusiness()"));
        }
    }

    @Slf4j
    @Component
    static class SmsApplicationListener implements ApplicationListener<MyEvent>
    {
        @Override
        public void onApplicationEvent(MyEvent event)
        {
            log.debug("发送短信");
        }
    }

    @Slf4j
    @Component
    static class EmailApplicationListener implements ApplicationListener<MyEvent>
    {
        @Override
        public void onApplicationEvent(MyEvent event)
        {
            log.debug("发送邮件");
        }
    }
}
