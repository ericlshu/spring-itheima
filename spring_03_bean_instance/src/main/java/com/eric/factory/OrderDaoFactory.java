package com.eric.factory;

import com.eric.dao.OrderDao;
import com.eric.dao.impl.OrderDaoImpl;

//静态工厂创建对象
public class OrderDaoFactory
{
    public static OrderDao getOrderDao()
    {
        System.out.println("factory setup....");
        return new OrderDaoImpl();
    }
}
