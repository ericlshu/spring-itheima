package com.eric.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-06 21:18
 * @since jdk-11.0.14
 */
@Data
@Component
@ConfigurationProperties(prefix = "cartoon")
public class TomAndJerry
{
    private Cat cat;
    private Mouse mouse;

    /*public TomAndJerry()
    {
        cat = new Cat("tom", 4);
        mouse = new Mouse("jerry", 3);
    }*/

    public void play()
    {
        System.out.println(cat.getAge() + "岁的" + cat.getName() + "和" + mouse.getAge() + "岁的" + mouse.getName() + "打起来了");
    }
}
