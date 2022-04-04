package com.eric.app;

import com.eric.bean.Cat;
import com.eric.bean.Mouse;
import com.eric.config.SpringConfig4;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : 上下文容器对象已经初始化完毕后，手工加载bean
 *
 * @author Eric L SHU
 * @date 2022-04-04 15:44
 * @since jdk-11.0.14
 */
public class App5
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig4.class);
        // 上下文容器对象已经初始化完毕后，手工加载bean
        context.registerBean("tom", Cat.class, 0);
        context.registerBean("tom", Cat.class, 1);
        context.registerBean("tom", Cat.class, 2);
        context.register(Mouse.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names)
        {
            System.out.println(name);
        }
        System.out.println("-----------------------------------------------------");
        // 后进入容器的对象会替换先进入的对象
        System.out.println(context.getBean(Cat.class));
    }
}
