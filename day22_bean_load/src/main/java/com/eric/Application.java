package com.eric;

import com.eric.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-06 19:47
 * @since jdk-11.0.14
 */
public class Application
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names)
        {
            System.out.println("name = " + name);
        }
    }
}
