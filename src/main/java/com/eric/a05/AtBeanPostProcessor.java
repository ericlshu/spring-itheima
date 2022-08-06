package com.eric.a05;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class AtBeanPostProcessor implements BeanFactoryPostProcessor
{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException
    {
        try
        {
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
            MetadataReader reader = factory.getMetadataReader(new ClassPathResource("com/eric/a05/Config.class"));
            Set<MethodMetadata> methods = reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());
            for (MethodMetadata method : methods)
            {
                log.info("method : {}", method);
                String methodName = method.getMethodName();

                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition()
                        .setFactoryMethodOnBean(methodName, "config")
                        .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);

                AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

                String initMethodName = Objects.requireNonNull(
                        method.getAnnotationAttributes(Bean.class.getName())).get("initMethod").toString();
                if (initMethodName != null && initMethodName.length() > 0)
                    // 执行bean的init方法创建数据源对象
                    beanDefinition.setInitMethodName(initMethodName);

                if (configurableListableBeanFactory instanceof DefaultListableBeanFactory beanFactory)
                    beanFactory.registerBeanDefinition(methodName, beanDefinition);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
