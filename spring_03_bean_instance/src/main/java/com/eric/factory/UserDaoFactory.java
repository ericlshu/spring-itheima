package com.eric.factory;

import com.eric.dao.UserDao;
import com.eric.dao.impl.UserDaoImpl;

//实例工厂创建对象
public class UserDaoFactory
{
    public UserDao getUserDao()
    {
        return new UserDaoImpl();
    }
}
