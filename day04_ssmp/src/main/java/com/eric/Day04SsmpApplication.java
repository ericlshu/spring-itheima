package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Eric SHU
 */
@SpringBootApplication
public class Day04SsmpApplication
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = SpringApplication.run(Day04SsmpApplication.class, args);
        /*String[] names = context.getBeanDefinitionNames();
        for (String name : names)
        {
            System.out.println("name = " + name);
        }*/
    }
}
