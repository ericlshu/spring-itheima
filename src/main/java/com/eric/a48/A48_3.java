package com.eric.a48;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * -> 根据监听器方法参数确定事件类型
 * -> 解析时机：在 SmartInitializingSingleton（所有单例初始化完成后），解析每个单例 bean
 */
@Slf4j
@Configuration
@SuppressWarnings("all")
public class A48_3
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A48_3.class);

        // for (String name : context.getBeanDefinitionNames())
        // {
        //     // SmsService bean = context.getBean(SmsService.class);
        //     // for (Method method : SmsService.class.getMethods())
        //     Object bean = context.getBean(name);
        //     for (Method method : bean.getClass().getMethods())
        //     {
        //         if (method.isAnnotationPresent(MyListener.class))
        //         {
        //             ApplicationListener listener = new ApplicationListener()
        //             {
        //                 @Override
        //                 public void onApplicationEvent(ApplicationEvent event)
        //                 {
        //                     log.info("event : {}", event);
        //                     Class<?> eventType = method.getParameterTypes()[0];
        //                     if (eventType.isAssignableFrom(event.getClass()))
        //                     {
        //                         try
        //                         {
        //                             method.invoke(bean, event);
        //                         }
        //                         catch (Exception e)
        //                         {
        //                             throw new RuntimeException(e);
        //                         }
        //                     }
        //                 }
        //             };
        //             context.addApplicationListener(listener);
        //         }
        //     }
        // }

        context.getBean(MyService.class).doBusiness();
        context.close();
    }

    // 所有的单例对象创建好之后回调该bean中的方法
    @Bean
    public SmartInitializingSingleton smartInitializingSingleton(ConfigurableApplicationContext context)
    {
        return () ->
        {
            for (String name : context.getBeanDefinitionNames())
            {
                Object bean = context.getBean(name);
                for (Method method : bean.getClass().getMethods())
                {
                    if (method.isAnnotationPresent(MyListener.class))
                    {
                        context.addApplicationListener((event) ->
                        {
                            log.info("event : {}", event);
                            // 监听器方法需要的事件类型
                            Class<?> eventType = method.getParameterTypes()[0];
                            if (eventType.isAssignableFrom(event.getClass()))
                            {
                                try
                                {
                                    method.invoke(bean, event);
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        };
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
            publisher.publishEvent(new MyEvent("MyService.doBusiness()"));
        }
    }

    @Slf4j
    @Component
    static class SmsService
    {
        @MyListener
        public void listener(MyEvent myEvent)
        {
            log.debug("发送短信");
        }
    }

    @Slf4j
    @Component
    static class EmailService
    {
        @MyListener
        public void listener(MyEvent myEvent)
        {
            log.debug("发送邮件");
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface MyListener
    {
    }

    static class MyEvent extends ApplicationEvent
    {
        public MyEvent(Object source)
        {
            super(source);
        }
    }
}
