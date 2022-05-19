package com.eric.factory;

import com.eric.dao.UserDao;
import com.eric.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.FactoryBean;

//FactoryBean创建对象
public class UserDaoFactoryBean implements FactoryBean<UserDao>
{
    //代替原始实例工厂中创建对象的方法，在方法中进行对象的创建并返回
    public UserDao getObject()
    {
        return new UserDaoImpl();
    }

    // 主要返回的是被创建类的Class对象
    public Class<?> getObjectType()
    {
        return UserDao.class;
    }

    // 默认是单例，只需要将isSingleton()方法进行重写，返回false即可实现多例模式
    @Override
    public boolean isSingleton()
    {
        return false;
    }
}
