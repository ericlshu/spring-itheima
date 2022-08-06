package com.eric.a05;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;

@Slf4j
public class MapperPostProcessor implements BeanDefinitionRegistryPostProcessor
{
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException
    {
        try
        {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath:com/eric/a05/mapper/**/*.class");
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
            AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();
            for (Resource resource : resources)
            {
                MetadataReader reader = factory.getMetadataReader(resource);
                ClassMetadata classMetadata = reader.getClassMetadata();
                String className = classMetadata.getClassName();
                log.debug("-->> className : {}", className);
                if (classMetadata.isInterface())
                {
                    AbstractBeanDefinition beanDefinition4Name = BeanDefinitionBuilder
                            .genericBeanDefinition(className)
                            .getBeanDefinition();
                    String beanName = generator.generateBeanName(beanDefinition4Name, beanFactory);

                    AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
                            .genericBeanDefinition(MapperFactoryBean.class)
                            .addConstructorArgValue(className)
                            .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                            .getBeanDefinition();

                    beanFactory.registerBeanDefinition(beanName, beanDefinition);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
    }
}
