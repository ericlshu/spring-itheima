package com.eric.a03;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestMethodTemplate
{
    public static void main(String[] args)
    {
        MyBeanFactory beanFactory = new MyBeanFactory();
        beanFactory.addBeanPostProcessor(bean -> log.debug("解析 @Autowired"));
        beanFactory.addBeanPostProcessor(bean -> log.debug("解析 @Resource"));
        beanFactory.getBean();
    }

    // 模板方法  Template Method Pattern
    static class MyBeanFactory
    {
        public Object getBean()
        {
            Object bean = new Object();
            log.debug("构造 " + bean);
            log.debug("依赖注入 " + bean);
            // 解析@Autowired, @Resource
            for (BeanPostProcessor processor : processors)
            {
                processor.inject(bean);
            }
            log.debug("初始化 " + bean);
            return bean;
        }

        private final List<BeanPostProcessor> processors = new ArrayList<>();

        public void addBeanPostProcessor(BeanPostProcessor processor)
        {
            processors.add(processor);
        }
    }

    interface BeanPostProcessor
    {
        /**
         * 对依赖注入阶段的扩展
         */
        void inject(Object bean);
    }
}
