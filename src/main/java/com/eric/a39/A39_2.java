package com.eric.a39;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.DefaultBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * a. 如何读取 spring.factories 中的配置
 * b. run 方法内获取事件发布器 (得到 SpringApplicationRunListeners) 的过程, 对应步骤中
 * -> 1.获取事件发布器
 * -> 发布 application starting 事件1️⃣
 * -> 发布 application environment 已准备事件2️⃣
 * -> 发布 application context 已初始化事件3️⃣
 * -> 发布 application prepared 事件4️⃣
 * -> 发布 application started 事件5️⃣
 * -> 发布 application ready 事件6️⃣
 * -> 这其中有异常，发布 application failed 事件7️⃣
 */
@Slf4j
public class A39_2
{
    public static void main(String[] args) throws Exception
    {
        // 添加 app 监听器
        SpringApplication app = new SpringApplication();
        app.addListeners(e -> log.debug(e.getClass().toString()));

        // 获取事件发送器实现类名
        List<String> names = SpringFactoriesLoader.loadFactoryNames(
                SpringApplicationRunListener.class, A39_2.class.getClassLoader());
        for (String name : names)
        {
            log.info("name : {}", name);
            Class<?> clazz = Class.forName(name);
            Constructor<?> constructor = clazz.getConstructor(SpringApplication.class, String[].class);
            SpringApplicationRunListener publisher = (SpringApplicationRunListener) constructor.newInstance(app, args);

            // 1. 发布事件
            DefaultBootstrapContext bootstrapContext = new DefaultBootstrapContext();
            // 2. spring boot 开始启动
            publisher.starting(bootstrapContext);
            // 3. 环境信息准备完毕
            publisher.environmentPrepared(bootstrapContext, new StandardEnvironment());
            GenericApplicationContext context = new GenericApplicationContext();
            // 4. 在 spring 容器创建，并调用初始化器之后，发送此事件
            publisher.contextPrepared(context);
            // 5. 所有 bean definition 加载完毕
            publisher.contextLoaded(context);
            context.refresh();
            // 6. spring 容器初始化完成(refresh 方法调用完毕)
            publisher.started(context);
            // 7. spring boot 启动完毕
            publisher.running(context);

            // 8. spring boot 启动出错
            publisher.failed(context, new Exception("出错了"));
        }
    }
}
