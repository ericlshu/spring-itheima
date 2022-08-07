package com.eric.a11;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * cglib 动态代理
 * -> cglib 不要求目标实现接口，它生成的代理类是目标的子类，因此代理与目标之间是子父关系
 * -> 限制⛔：根据上述分析 final 类无法被 cglib 增强（运行时错误）, final方法无法被增强（运行不报错）
 */
@Slf4j
public class CglibProxyDemo
{
    // 代理是子类型, 目标是父类型
    public static void main(String[] param)
    {
        Target target = new Target();

        Target proxy = (Target) Enhancer.create(
                Target.class,
                (MethodInterceptor) (proxyObj, method, args, methodProxy) ->
                {
                    log.debug("before...");

                    // 1. 用方法反射调用目标
                    // Object result = method.invoke(target, args);

                    // methodProxy 它可以避免反射调用
                    // 2. 内部没有用反射, 需要目标, Spring 使用此方式
                    Object result = methodProxy.invoke(target, args);

                    // 3. 内部没有用反射, 需要代理
                    // Object result = methodProxy.invokeSuper(proxyObj, args);

                    log.debug("after...");
                    return result;
                });
        log.debug("proxy class : {}", proxy);
        proxy.foo();
    }

    static class Target
    {
        public void foo()
        {
            log.debug("target foo");
        }

        @Override
        public String toString()
        {
            return "Target{}";
        }
    }
}
