package com.eric.app;

import com.eric.bean.Dog;
import com.eric.config.SpringConfig4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : 使用@Import注解导入要注入的bean对应的字节码
 * 此形式可以有效的降低源代码与Spring技术的耦合度，在spring技术底层及诸多框架的整合中大量使用
 *
 * @author Eric L SHU
 * @date 2022-04-04 15:44
 * @since jdk-11.0.14
 */
public class App4
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig4.class);

        String[] names = context.getBeanDefinitionNames();
        for (String name : names)
        {
            System.out.println(name);
        }
        System.out.println("-----------------------------------------------------");
        System.out.println(context.getBean(Dog.class));
    }
}
