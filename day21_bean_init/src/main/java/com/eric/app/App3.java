package com.eric.app;

import com.eric.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-04 15:44
 * @since jdk-11.0.14
 */
public class App3
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : definitionNames)
        {
            System.out.println("definitionName = " + definitionName);
        }
    }
}
