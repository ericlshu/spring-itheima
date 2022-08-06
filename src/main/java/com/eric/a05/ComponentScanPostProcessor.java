package com.eric.a05;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class ComponentScanPostProcessor implements BeanFactoryPostProcessor
{
    @Override // context.refresh执行时调用该方法
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException
    {
        try
        {
            ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
            if (componentScan != null)
            {
                for (String basePackage : componentScan.basePackages())
                {
                    log.debug("basePackage  : {}", basePackage);
                    String resourcePath = "classpath*:" + basePackage.replace(".", "/") + "/**/*.class";
                    log.debug("resourcePath : {}", resourcePath);
                    CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
                    // Resource[] resources = context.getResources(resourcePath);
                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(resourcePath);
                    BeanNameGenerator generator = new AnnotationBeanNameGenerator();
                    for (Resource resource : resources)
                    {
                        log.info("resource : {}", resource);
                        MetadataReader reader = factory.getMetadataReader(resource);
                        String className = reader.getClassMetadata().getClassName();
                        log.debug("-->> className : {}", className);
                        AnnotationMetadata metadata = reader.getAnnotationMetadata();
                        boolean hasAnnotation = metadata.hasAnnotation(Component.class.getName());
                        log.debug("-->> hasAnnotation : {}", hasAnnotation);
                        boolean hasMetaAnnotation = metadata.hasMetaAnnotation(Component.class.getName());
                        log.debug("-->> hasMetaAnnotation : {}", hasMetaAnnotation);
                        if (hasAnnotation || hasMetaAnnotation)
                        {
                            AbstractBeanDefinition beanDefinition =
                                    BeanDefinitionBuilder.genericBeanDefinition(className).getBeanDefinition();
                            // DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
                            if (configurableListableBeanFactory instanceof DefaultListableBeanFactory beanFactory)
                            {
                                String beanName = generator.generateBeanName(beanDefinition, beanFactory);
                                beanFactory.registerBeanDefinition(beanName, beanDefinition);
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
