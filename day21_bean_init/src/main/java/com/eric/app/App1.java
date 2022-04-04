package com.eric.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-04 15:44
 * @since jdk-11.0.14
 */
public class App1
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext1.xml");

        // Cat cat = (Cat) applicationContext.getBean("cat");
        // System.out.println("cat = " + cat);
        //
        // Dog dog = applicationContext.getBean(Dog.class);
        // System.out.println("dog = " + dog);

        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : definitionNames)
        {
            System.out.println("definitionName = " + definitionName);
        }
    }
}
