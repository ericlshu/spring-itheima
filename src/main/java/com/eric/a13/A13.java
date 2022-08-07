package com.eric.a13;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class A13
{
    public static void main(String[] args)
    {
        Proxy proxy = new Proxy();
        Target target = new Target();

        proxy.setMethodInterceptor(new MethodInterceptor()
        {
            @Override
            public Object intercept(Object proxyObj, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable
            {
                log.info("proxy before...");
                method.invoke(target, args); // 反射调用
                // FastClass
                methodProxy.invoke(target, args); // 内部无反射, 结合目标用
                methodProxy.invokeSuper(proxyObj, args); // 内部无反射, 结合代理用
                log.info("proxy after...");
                return null;
            }
        });

        proxy.save();
        proxy.save(100);
        proxy.save(200L);
    }
}
