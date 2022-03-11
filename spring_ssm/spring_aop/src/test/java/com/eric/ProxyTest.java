package com.eric;

import com.eric.proxy.jdk.Advice;
import com.eric.proxy.jdk.Target;
import com.eric.proxy.jdk.TargetInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 12:35
 */
public class ProxyTest {

    public static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testJdk()
    {
        Target target = new Target(); // 目标对象
        Advice advice = new Advice(); // 增强对象

        // 返回动态生成的代理对象
        TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标对象的类加载器
                target.getClass().getInterfaces(),  // 目标对象相同的接口字节码对象数组
                new InvocationHandler() {
                    /**
                     * 调用代理对象的任何方法 实质执行的都是invoke方法
                     * @param proxy  代理对象
                     * @param method 目标方法对象
                     * @param args   目标方法参数
                     * @return method方法的返回值 void则为null
                     * @throws Throwable exception
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                    {
                        advice.before(); //前置增强
                        Object obj = method.invoke(target, args);
                        advice.after();  //后置增强
                        return obj;
                    }
                }
        );

        // 调用代理对象的方法
        String save = proxy.save();
        LOGGER.debug(save);
    }

    @Test
    public void testCglib()
    {
        Target target = new Target(); // 目标对象
        Advice advice = new Advice(); // 增强对象

        // 1.创建增强器
        Enhancer enhancer = new Enhancer();

        // 2.设置父类（目标）
        enhancer.setSuperclass(Target.class);

        // 3.设置回调
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            advice.before();
            Object invoke = method.invoke(target, objects);
            advice.after();
            return invoke;
        });

        // 4.创建代理对象
        Target proxy = (Target) enhancer.create();
        proxy.save();
    }
}
