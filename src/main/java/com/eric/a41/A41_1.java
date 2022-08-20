package com.eric.a41;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.io.IOException;
import java.util.List;

/**
 * 使用@Import({ImportSelector.class})导入自动配置类并加载bean会优先于当前项目配置类，后加载的bean会覆盖先加载的bean
 * Spring默认使用不允许后加载的bean覆盖先加载的bean，因此使用DeferredImportSelector保证当前配置优先加载
 * 使用@ConditionalOnMissingBean注解，当前配置类不存在该bean定义时，加载默认配置类
 */
@Slf4j
public class A41_1
{
    public static void main(String[] args) throws IOException
    {
        GenericApplicationContext context = new GenericApplicationContext();

        // 设置不允许后加载的bean覆盖先加载的bean
        context.getDefaultListableBeanFactory().setAllowBeanDefinitionOverriding(false);

        context.registerBean("config", Config.class);
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.refresh();

        for (String name : context.getBeanDefinitionNames())
        {
            log.debug("Bean in Context : {}", name);
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.debug(String.valueOf(context.getBean(Bean1.class)));
    }

    @Configuration // 本项目的配置类
    // @Import({AutoConfiguration1.class, AutoConfiguration2.class})
    @Import(MyImportSelector.class)
    static class Config
    {
        @Bean
        public Bean1 bean1()
        {
            return new Bean1("本项目");
        }
    }

    // static class MyImportSelector implements ImportSelector
    static class MyImportSelector implements DeferredImportSelector
    {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata)
        {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            for (String name : SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, null))
            {
                log.debug("AutoConfig class : {}", name);
            }
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            // return new String[]{AutoConfiguration1.class.getName(), AutoConfiguration2.class.getName()};

            List<String> names = SpringFactoriesLoader.loadFactoryNames(MyImportSelector.class, null);
            return names.toArray(new String[0]);
        }
    }

    @Configuration // 第三方的配置类
    static class AutoConfiguration1
    {
        @Bean
        @ConditionalOnMissingBean
        public Bean1 bean1()
        {
            return new Bean1("第三方");
        }
    }

    @Configuration // 第三方的配置类
    static class AutoConfiguration2
    {
        @Bean
        public Bean2 bean2()
        {
            return new Bean2();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Bean1
    {
        private String name;
    }

    static class Bean2
    {
    }
}
