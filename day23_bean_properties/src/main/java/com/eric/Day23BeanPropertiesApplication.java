package com.eric;

import com.eric.bean.TomAndJerry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(excludeName = "org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration")
// @Import(TomAndJerry.class)
public class Day23BeanPropertiesApplication
{
    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = SpringApplication.run(Day23BeanPropertiesApplication.class, args);
        TomAndJerry cartoon = context.getBean(TomAndJerry.class);
        cartoon.play();
    }
}
