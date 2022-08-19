package com.eric.a39;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.*;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;

/**
 * 阶段二：执行 run 方法
 * 1. 得到 SpringApplicationRunListeners，名字取得不好，实际是事件发布器
 * -> 发布 application starting 事件1️⃣
 * 2. 封装启动 args
 * 3. 准备 Environment 添加命令行参数（*）
 * 4. ConfigurationPropertySources 处理（*）
 * -> 发布 application environment 已准备事件2️⃣
 * 5. 通过 EnvironmentPostProcessorApplicationListener 进行 env 后处理（*）
 * -> application.properties，由 StandardConfigDataLocationResolver 解析
 * -> spring.application.json
 * 6. 绑定 spring.main 到 SpringApplication 对象（*）
 * 7. 打印 banner（*）
 * 8. 创建容器
 * 9. 准备容器
 * -> 发布 application context 已初始化事件3️⃣
 * 10. 加载 bean 定义
 * -> 发布 application prepared 事件4️⃣
 * 11. refresh 容器
 * -> 发布 application started 事件5️⃣
 * 12. 执行 runner
 * -> 发布 application ready 事件6️⃣
 * -> 这其中有异常，发布 application failed 事件7️⃣
 */
// 运行时请添加运行参数 --server.port=8080 debug
@Slf4j
@SuppressWarnings("all")
public class A39_3
{
    public static void main(String[] args) throws Exception
    {
        SpringApplication app = new SpringApplication();
        app.addInitializers(new ApplicationContextInitializer<ConfigurableApplicationContext>()
        {
            @Override
            public void initialize(ConfigurableApplicationContext applicationContext)
            {
                log.debug("执行初始化器增强...");
            }
        });

        log.info(">>>>>>>>>>>>>>>>>>>>>>>> 2. 封装启动 args");
        DefaultApplicationArguments arguments = new DefaultApplicationArguments(args);

        log.info(">>>>>>>>>>>>>>>>>>>>>>>> 8. 创建容器");
        GenericApplicationContext context = createApplicationContext(WebApplicationType.SERVLET);

        log.info(">>>>>>>>>>>>>>>>>>>>>>>> 9. 准备容器");
        for (ApplicationContextInitializer initializer : app.getInitializers())
        {
            initializer.initialize(context);
        }

        log.info(">>>>>>>>>>>>>>>>>>>>>>>> 10. 加载 bean 定义");
        DefaultListableBeanFactory beanFactory = context.getDefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader1 = new AnnotatedBeanDefinitionReader(beanFactory);
        XmlBeanDefinitionReader reader2 = new XmlBeanDefinitionReader(beanFactory);
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanFactory);

        reader1.register(Config.class);
        reader2.loadBeanDefinitions(new ClassPathResource("b03.xml"));
        scanner.scan("com.eric.a39.sub");

        log.info(">>>>>>>>>>>>>>>>>>>>>>>> 11. refresh 容器");
        context.refresh();

        for (String name : context.getBeanDefinitionNames())
        {
            log.debug("name:" + name + " 来源：" + beanFactory.getBeanDefinition(name).getResourceDescription());
        }

        log.info(">>>>>>>>>>>>>>>>>>>>>>>> 12. 执行 runner");
        for (CommandLineRunner runner : context.getBeansOfType(CommandLineRunner.class).values())
        {
            runner.run(args);
        }
        for (ApplicationRunner runner : context.getBeansOfType(ApplicationRunner.class).values())
        {
            runner.run(arguments);
        }
    }

    private static GenericApplicationContext createApplicationContext(WebApplicationType type)
    {
        GenericApplicationContext context = null;
        switch (type)
        {
            case SERVLET -> context = new AnnotationConfigServletWebServerApplicationContext();
            case REACTIVE -> context = new AnnotationConfigReactiveWebServerApplicationContext();
            case NONE -> context = new AnnotationConfigApplicationContext();
        }
        return context;
    }

    static class Bean4
    {
    }

    static class Bean5
    {
    }

    static class Bean6
    {
    }

    @Configuration
    static class Config
    {
        @Bean
        public Bean5 bean5()
        {
            return new Bean5();
        }

        @Bean
        public ServletWebServerFactory servletWebServerFactory()
        {
            return new TomcatServletWebServerFactory();
        }

        @Bean
        public CommandLineRunner commandLineRunner()
        {
            return args -> log.info("commandLineRunner()..." + Arrays.toString(args));
        }

        @Bean
        public ApplicationRunner applicationRunner()
        {
            return args ->
            {
                log.info("applicationRunner()..." + Arrays.toString(args.getSourceArgs()));
                // 获取以“--”开头的参数
                log.info("Option Arg name  : {}", args.getOptionNames());
                log.info("Option Arg value : {}", args.getOptionValues("server.port"));
                log.info("Non-Option Args  : {}", args.getNonOptionArgs());
            };
        }
    }
}
