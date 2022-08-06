package com.eric.a05;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

/*
 * BeanFactory 后处理器的作用
 * -> a. @ComponentScan, @Bean, @Mapper 等注解的解析属于核心容器(即 BeanFactory)的扩展功能
 * -> b. 这些扩展功能由不同的 BeanFactory 后处理器来完成, 其实主要就是补充了一些 bean 定义
 */
@Slf4j
public class A05
{
    public static void main(String[] args) throws IOException
    {
        // ⬇️GenericApplicationContext 是一个【干净】的容器
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

        // 解析 @ComponentScan @Bean @Import @ImportResource 注解
        // context.registerBean(ConfigurationClassPostProcessor.class);
        // 解析 @MapperScanner 扫描mapper接口
        // context.registerBean(MapperScannerConfigurer.class, beanDefinition ->
        //         beanDefinition.getPropertyValues().add("basePackage", "com.eric.a05.mapper")
        // );

        // ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        // if (componentScan != null)
        // {
        //     for (String basePackage : componentScan.basePackages())
        //     {
        //         log.debug("basePackage  : {}", basePackage);
        //         String resourcePath = "classpath*:" + basePackage.replace(".", "/") + "/**/*.class";
        //         log.debug("resourcePath : {}", resourcePath);
        //         CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        //         Resource[] resources = context.getResources(resourcePath);
        //         BeanNameGenerator generator = new AnnotationBeanNameGenerator();
        //         for (Resource resource : resources)
        //         {
        //             log.info("resource : {}", resource);
        //             MetadataReader reader = factory.getMetadataReader(resource);
        //             String className = reader.getClassMetadata().getClassName();
        //             log.debug("-->> className : {}", className);
        //             AnnotationMetadata metadata = reader.getAnnotationMetadata();
        //             boolean hasAnnotation = metadata.hasAnnotation(Component.class.getName());
        //             log.debug("-->> hasAnnotation : {}", hasAnnotation);
        //             boolean hasMetaAnnotation = metadata.hasMetaAnnotation(Component.class.getName());
        //             log.debug("-->> hasMetaAnnotation : {}", hasMetaAnnotation);
        //             if (hasAnnotation || hasMetaAnnotation)
        //             {
        //                 DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        //                 AbstractBeanDefinition beanDefinition =
        //                         BeanDefinitionBuilder.genericBeanDefinition(className).getBeanDefinition();
        //                 String beanName = generator.generateBeanName(beanDefinition, beanFactory);
        //                 beanFactory.registerBeanDefinition(beanName, beanDefinition);
        //             }
        //         }
        //     }
        // }

        // 使用自定义bean后处理器解析 @ComponentScan
        context.registerBean(ComponentScanPostProcessor.class);

        // ⬇️初始化容器
        context.refresh();

        for (String name : context.getBeanDefinitionNames())
        {
            log.warn(name);
        }
    }
}
