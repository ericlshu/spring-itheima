package com.eric.bean;

import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-06 21:18
 * @since jdk-11.0.14
 */
@Component
public class TomAndJerry
{
    private Cat tom;
    private Mouse jerry;

    public void play()
    {
        System.out.println("4岁的tom和3岁的jerry打起来了");
    }
}
