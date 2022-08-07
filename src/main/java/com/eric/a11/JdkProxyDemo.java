package com.eric.a11;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * 0. jdk 只能针对接口代理，cglib 则没有限制
 * 1. jdk 动态代理要求目标必须实现接口，生成的代理类实现相同接口，因此代理与目标之间是平级兄弟关系
 * 2. 目标类可以是final类
 */
@Slf4j
public class JdkProxyDemo
{
    public static void main(String[] param) throws IOException
    {
        // 目标对象
        Target target = new Target();

        ClassLoader loader = JdkProxyDemo.class.getClassLoader();
        Foo proxy = (Foo) Proxy.newProxyInstance(
                loader, // 当前类的类加载器，用来加载在运行期间动态生成的字节码
                new Class[]{Foo.class}, // 目标对象的父接口列表
                // 调用具体方的InvocationHandler对象
                (p, method, args) ->
                {
                    log.debug("before...");
                    // 普通方式调用方式：目标.方法(参数)
                    // 反射方式调用方式：方法.invoke(目标, 参数);
                    Object result = method.invoke(target, args);
                    log.debug("after....");
                    return result; // 让代理也返回目标方法执行的结果
                });

        log.debug(proxy.getClass().toString());

        proxy.foo();

        int read = System.in.read();
    }

    interface Foo
    {
        void foo();
    }

    static final class Target implements Foo
    {
        public void foo()
        {
            log.debug("target foo() ...");
        }
    }
}
