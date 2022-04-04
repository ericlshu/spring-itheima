package com.eric.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Description : 初始化实现FactoryBean接口的类，实现对bean加载到容器之前的批处理操作
 *
 * @author Eric L SHU
 * @date 2022-04-04 17:25
 * @since jdk-11.0.14
 */
public class DogFactoryBean implements FactoryBean<Dog>
{
    @Override
    public Dog getObject()
    {
        return new Dog();
    }

    @Override
    public Class<?> getObjectType()
    {
        return Dog.class;
    }

    @Override
    public boolean isSingleton()
    {
        return true;
    }
}
