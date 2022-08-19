package org.springframework.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor;
import org.springframework.boot.context.event.EventPublishingRunListener;
import org.springframework.boot.env.EnvironmentPostProcessorApplicationListener;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.boot.logging.DeferredLogs;
import org.springframework.core.env.PropertySource;

/*
 * 可以添加参数 --spring.application.json={\"server\":{\"port\":9090}}
 * 测试 SpringApplicationJsonEnvironmentPostProcessor
 */
@Slf4j
public class Step5
{
    public static void main(String[] args)
    {
        // test1();// hard code
        test2(args);// read conf
    }

    private static void test2(String[] args)
    {
        SpringApplication app = new SpringApplication();
        app.addListeners(new EnvironmentPostProcessorApplicationListener());

        /*List<String> names = SpringFactoriesLoader.loadFactoryNames(
                EnvironmentPostProcessor.class, Step5.class.getClassLoader());
        for (String name : names)
        {
            log.info(name);
        }*/

        EventPublishingRunListener publisher = new EventPublishingRunListener(app, args);
        ApplicationEnvironment env = new ApplicationEnvironment();

        log.warn(">>>>>>>>>>>>>>>>>>>>>>>>> 增强前");
        for (PropertySource<?> ps : env.getPropertySources())
        {
            log.info("PropertySource : {}", ps);
        }
        publisher.environmentPrepared(new DefaultBootstrapContext(), env);

        log.warn(">>>>>>>>>>>>>>>>>>>>>>>>> 增强后");
        for (PropertySource<?> ps : env.getPropertySources())
        {
            log.info("PropertySource : {}", ps);
        }
    }


    private static void test1()
    {
        SpringApplication app = new SpringApplication();
        ApplicationEnvironment env = new ApplicationEnvironment();

        log.warn(">>>>>>>>>>>>>>>>>>>>>>>>> 增强前");
        for (PropertySource<?> ps : env.getPropertySources())
        {
            log.info("PropertySource : {}", ps);
        }
        ConfigDataEnvironmentPostProcessor postProcessor1 = new ConfigDataEnvironmentPostProcessor(
                new DeferredLogs(), new DefaultBootstrapContext());
        postProcessor1.postProcessEnvironment(env, app);

        log.warn(">>>>>>>>>>>>>>>>>>>>>>>>> 增强后");
        for (PropertySource<?> ps : env.getPropertySources())
        {
            log.info("PropertySource : {}", ps);
        }
        RandomValuePropertySourceEnvironmentPostProcessor postProcessor2 =
                new RandomValuePropertySourceEnvironmentPostProcessor(new DeferredLog());
        postProcessor2.postProcessEnvironment(env, app);

        log.warn(">>>>>>>>>>>>>>>>>>>>>>>>> 增强后");
        for (PropertySource<?> ps : env.getPropertySources())
        {
            log.info("PropertySource : {}", ps);
        }

        log.info(env.getProperty("server.port"));
        log.info(env.getProperty("random.int"));
        log.info(env.getProperty("random.int"));
        log.info(env.getProperty("random.uuid"));
        log.info(env.getProperty("random.uuid"));
    }
}
