package com.eric.a10;

import com.eric.a10.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/*
 * 注意几点
 * 1. 版本选择了 java 8, 因为目前的 aspectj-maven-plugin 1.14.0 最高只支持到 java 16
 * 2. 运行时需要在 VM options 里加入 -javaagent:D:/Sandbox/Repository/org/aspectj/aspectjweaver/1.9.7/aspectjweaver-1.9.7.jar
 */
@Slf4j
@SpringBootApplication
public class A10
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = SpringApplication.run(A10.class, args);
        MyService service = context.getBean(MyService.class);

        // ⬇️MyService 并非代理, 但 foo 方法也被增强了, 做增强的 java agent, 在加载类时, 修改了 class 字节码
        // ⬇️foo方法中调用的bar方法也被增强，因为没有经过动态代理，而是直接改了目标类的字节码文件
        log.debug("service class: {}", service.getClass());
        service.foo();

        /*
            学到了什么
            1. aop 的原理并非代理一种, agent 也能, 只要字节码变了, 行为就变了
         */
    }
}
