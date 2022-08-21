package com.eric.a47;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * 1. @Autowired 本质上是根据成员变量或方法参数的类型进行装配
 * 2. 如果待装配类型是 Optional，需要根据 Optional 泛型找到 bean，再封装为 Optional 对象装配
 * 3. 如果待装配的类型是 ObjectFactory，需要根据 ObjectFactory 泛型创建 ObjectFactory 对象装配
 * -> 此方法可以延迟 bean 的获取
 * 4. 如果待装配的成员变量或方法参数上用 @Lazy 标注，会创建代理对象装配
 * -> 此方法可以延迟真实 bean 的获取
 * -> 被装配的代理不作为 bean
 */
@Slf4j
@Configuration
@SuppressWarnings("all")
public class A47_1
{
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A47_1.class);
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // 1. 根据成员变量的类型注入
        DependencyDescriptor dd1 = new DependencyDescriptor(Bean1.class.getDeclaredField("bean2"), false);
        log.debug("根据成员变量的类型注入bean2 : {}", beanFactory.doResolveDependency(dd1, "bean1", null, null));

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // 2. 根据方法参数的类型注入
        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean2, 0), false);
        log.debug("根据方法参数的类型注入bean2 : {}", beanFactory.doResolveDependency(dd2, "bean1", null, null));

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // 3. 结果包装为 Optional<Bean2>
        DependencyDescriptor dd3 = new DependencyDescriptor(Bean1.class.getDeclaredField("bean3"), false);
        if (dd3.getDependencyType() == Optional.class)
        {
            dd3.increaseNestingLevel(); // 进入内嵌层级
            Object result = beanFactory.doResolveDependency(dd3, "bean1", null, null);
            log.debug("结果包装为Optional<Bean2> : {}", Optional.ofNullable(result));
        }

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // 4. 结果包装为 ObjectProvider,ObjectFactory
        DependencyDescriptor dd4 = new DependencyDescriptor(Bean1.class.getDeclaredField("bean4"), false);
        if (dd4.getDependencyType() == ObjectFactory.class)
        {
            dd4.increaseNestingLevel();
            ObjectFactory objectFactory = new ObjectFactory()
            {
                @Override
                public Object getObject() throws BeansException
                {
                    return beanFactory.doResolveDependency(dd4, "bean1", null, null);
                }
            };
            log.debug("结果包装为ObjectFactory : {}", objectFactory.getObject());
        }

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // 5. 对 @Lazy 的处理
        DependencyDescriptor dd5 = new DependencyDescriptor(Bean1.class.getDeclaredField("bean2"), false);
        ContextAnnotationAutowireCandidateResolver resolver = new ContextAnnotationAutowireCandidateResolver();
        resolver.setBeanFactory(beanFactory);
        Object proxy = resolver.getLazyResolutionProxyIfNecessary(dd5, "bean1");
        log.debug("对@Lazy生成代理对象proxy : {}", proxy);
        log.debug("对@Lazy生成代理对象的类型 : {}", proxy.getClass());
    }

    static class Bean1
    {
        @Autowired
        @Lazy
        private Bean2 bean2;

        @Autowired
        public void setBean2(@Lazy Bean2 bean2)
        {
            this.bean2 = bean2;
        }

        @Autowired
        private Optional<Bean2> bean3;
        @Autowired
        private ObjectFactory<Bean2> bean4;
    }

    @Component("bean2")
    static class Bean2
    {
        /*@Override
        public String toString() {
            return super.toString();
        }*/
    }
}
