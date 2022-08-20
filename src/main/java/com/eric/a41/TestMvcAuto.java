package com.eric.a41;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 1. ServletWebServerFactoryAutoConfiguration
 * -> 提供 ServletWebServerFactory
 * <p>
 * 2. DispatcherServletAutoConfiguration
 * -> 提供 DispatcherServlet
 * -> 提供 DispatcherServletRegistrationBean
 * <p>
 * 3. WebMvcAutoConfiguration
 * 配置 DispatcherServlet 的各项组件，提供的 bean 见过的有
 * -> 多项 HandlerMapping
 * -> 多项 HandlerAdapter
 * -> HandlerExceptionResolver
 * <p>
 * 4. ErrorMvcAutoConfiguration
 * -> 提供的 bean 有 BasicErrorController
 */
@Slf4j
public class TestMvcAuto
{
    public static void main(String[] args)
    {
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext();
        context.registerBean(Config.class);
        context.refresh();
        for (String name : context.getBeanDefinitionNames())
        {
            String source = context.getBeanDefinition(name).getResourceDescription();
            if (source != null)
            {
                log.info(name + "<from:>" + source);
            }
        }
        context.close();
    }

    @Configuration
    @Import(MyImportSelector.class)
    static class Config
    {
    }

    static class MyImportSelector implements DeferredImportSelector
    {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata)
        {
            return new String[]{
                    ServletWebServerFactoryAutoConfiguration.class.getName(),
                    DispatcherServletAutoConfiguration.class.getName(),
                    WebMvcAutoConfiguration.class.getName(),
                    ErrorMvcAutoConfiguration.class.getName()
            };
        }
    }
}
