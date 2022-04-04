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
public class App2
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext2.xml");
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : definitionNames)
        {
            System.out.println("definitionName = " + definitionName);
        }
    }
}
