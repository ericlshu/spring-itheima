package com.eric;

import com.eric.config.ServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Eric SHU
 */
@SpringBootApplication
public class Day07AdvanceConfigApplication {

    public static void main(String[] args)
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Day07AdvanceConfigApplication.class, args);
        ServerConfiguration serverConfiguration = applicationContext.getBean(ServerConfiguration.class);
        System.out.println("serverConfiguration = " + serverConfiguration);
    }

}
