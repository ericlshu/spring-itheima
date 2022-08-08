package com.eric.a16;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

@Slf4j
public class A16
{
    public static void main(String[] args) throws NoSuchMethodException
    {
        log.info("***********************************");

        AspectJExpressionPointcut pt1 = new AspectJExpressionPointcut();
        pt1.setExpression("execution(* bar())");
        log.debug("T1.foo() matched execution : {}", pt1.matches(T1.class.getMethod("foo"), T1.class));
        log.debug("T1.bar() matched execution : {}", pt1.matches(T1.class.getMethod("bar"), T1.class));

        log.info("***********************************");

        AspectJExpressionPointcut pt2 = new AspectJExpressionPointcut();
        pt2.setExpression("@annotation(org.springframework.transaction.annotation.Transactional)");
        log.debug("T1.foo() matched annotation : {}", pt2.matches(T1.class.getMethod("foo"), T1.class));
        log.debug("T1.bar() matched annotation : {}", pt2.matches(T1.class.getMethod("bar"), T1.class));

        StaticMethodMatcherPointcut pt3 = new StaticMethodMatcherPointcut()
        {
            @Override
            public boolean matches(Method method, Class<?> targetClass)
            {
                // 检查方法上是否加了 Transactional 注解
                MergedAnnotations annotations = MergedAnnotations.from(method);
                if (annotations.isPresent(Transactional.class))
                    return true;
                // 查看类上是否加了 Transactional 注解
                annotations = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                return annotations.isPresent(Transactional.class);
            }
        };

        log.info("***********************************");

        log.debug("T1.foo() matched annotation : {}", pt3.matches(T1.class.getMethod("foo"), T1.class));
        log.debug("T1.bar() matched annotation : {}", pt3.matches(T1.class.getMethod("bar"), T1.class));
        log.debug("T2.foo() matched annotation : {}", pt3.matches(T2.class.getMethod("foo"), T2.class));
        log.debug("T3.foo() matched annotation : {}", pt3.matches(T3.class.getMethod("foo"), T3.class));

        log.info("***********************************");

        /*
            学到了什么
                a. 底层切点实现是如何匹配的: 调用了 aspectj 的匹配方法
                b. 比较关键的是它实现了 MethodMatcher 接口, 用来执行方法的匹配
         */
    }

    static class T1
    {
        @Transactional
        public void foo()
        {
        }

        public void bar()
        {
        }
    }

    @Transactional
    static class T2
    {
        public void foo()
        {
        }
    }

    @Transactional
    interface I3
    {
        void foo();
    }

    static class T3 implements I3
    {
        public void foo()
        {
        }
    }
}
