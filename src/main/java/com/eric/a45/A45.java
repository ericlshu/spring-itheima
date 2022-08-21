package com.eric.a45;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.Advised;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;

/*
 * Spring代理的设计特点
 * -> 依赖注入和初始化影响的是原始对象
 * -> 代理与目标是两个对象，二者成员变量并不共用数据
 */
@Slf4j
@SpringBootApplication
public class A45
{
    public static void main(String[] args) throws Exception
    {
        ConfigurableApplicationContext context = SpringApplication.run(A45.class, args);

        // 依赖注入和初始化影响的是原始对象，不会执行增强方法
        Bean1 proxy = context.getBean(Bean1.class);

        // 代理对象调用会执行增强方法
        // proxy.setBean2(new Bean2());
        // proxy.init();

        // 代理与目标是两个对象，二者成员变量并不共用数据
        showProxyAndTarget(proxy);

        log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        log.debug(String.valueOf(proxy.getBean2()));
        log.debug(String.valueOf(proxy.isInitialized()));

        log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // static 方法、final 方法、private 方法均无法增强

        proxy.m1();
        proxy.m2();
        proxy.m3();
        Method m4 = Bean1.class.getDeclaredMethod("m4");
        m4.setAccessible(true);
        m4.invoke(proxy);

        Method m5 = Bean1.class.getDeclaredMethod("m1");
        m5.setAccessible(true);
        m5.invoke(proxy);

        context.close();
    }

    public static void showProxyAndTarget(Bean1 proxy) throws Exception
    {
        log.debug(">>>>> 代理中的成员变量");
        log.debug("\tinitialized=" + proxy.initialized);
        log.debug("\tbean2=" + proxy.bean2);

        if (proxy instanceof Advised advised)
        {
            log.debug(">>>>> 目标中的成员变量");
            Bean1 target = (Bean1) advised.getTargetSource().getTarget();
            log.debug("\tinitialized=" + target.initialized);
            log.debug("\tbean2=" + target.bean2);
        }
    }
}
