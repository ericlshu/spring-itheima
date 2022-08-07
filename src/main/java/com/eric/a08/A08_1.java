package com.eric.a08;

import com.eric.a08.sub.E;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/*
 * 如果 jdk > 8, 运行时请添加 --add-opens java.base/java.lang=ALL-UNNAMED
 * -> a. 单例注入其它 scope 的四种失效场景及解决方法
 * -> b. 解决方法虽然不同, 但理念上殊途同归: 都是推迟其它 scope bean 的获取
 */
@Slf4j
@ComponentScan("com.eric.a08.sub")
public class A08_1
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A08_1.class);

        // 单例注入多例对象时，多例的配置会失效；
        E e = context.getBean(E.class);

        // 使用@Lazy注解注入代理对象，在真正使用代理对象的方法时，创建多例对象
        log.info("{}", e.getF1().getClass());
        log.debug("{}", e.getF1());
        log.debug("{}", e.getF1());

        // 在多例类型的scope配置中加入proxyMode = ScopedProxyMode.TARGET_CLASS
        log.info("{}", e.getF2().getClass());
        log.debug("{}", e.getF2());
        log.debug("{}", e.getF2());

        // 使用ObjectFactory解创建多例对象
        log.info("{}", e.getF3().getClass());
        log.debug("{}", e.getF3());
        log.debug("{}", e.getF3());

        // 使用ApplicationContext容器获取多例对象
        log.info("{}", e.getF4().getClass());
        log.debug("{}", e.getF4());
        log.debug("{}", e.getF4());

        context.close();
    }
}
