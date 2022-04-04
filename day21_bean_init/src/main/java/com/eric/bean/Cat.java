package com.eric.bean;

import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-04 12:51
 * @since jdk-11.0.14
 */
@Component("tom")
public class Cat
{
    private int age;

    public Cat()
    {
    }

    public Cat(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "Cat{" +
                "age=" + age +
                '}';
    }
}
