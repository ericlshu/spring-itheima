package com.eric.app;

import com.eric.bean.Dog;
import com.eric.config.SpringConfig7;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : 导入实现了ImportSelector接口的类，实现对导入源的编程式处理
 *
 * @author Eric L SHU
 * @date 2022-04-04 15:44
 * @since jdk-11.0.14
 */
public class App7
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig7.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names)
        {
            System.out.println(name);
        }
        System.out.println("-----------------------------------------------------");
        Dog johnson = context.getBean(Dog.class);
        System.out.println("johnson = " + johnson);
    }
}
