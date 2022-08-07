package com.eric.a07;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 销毁之后, 仍可创建新的单例
 */
@Slf4j
public class A07_2
{
    public static void main(String[] args)
    {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("myBean",
                BeanDefinitionBuilder.genericBeanDefinition(MyBean.class)
                        .setDestroyMethodName("destroy")
                        .getBeanDefinition()
        );

        log.debug(beanFactory.getBean(MyBean.class).toString());
        beanFactory.destroySingletons();
        log.debug(beanFactory.getBean(MyBean.class).toString());
    }

    static class MyBean
    {
        public MyBean()
        {
            log.debug("MyBean()");
        }

        public void destroy()
        {
            log.debug("destroy()");
        }
    }
}
