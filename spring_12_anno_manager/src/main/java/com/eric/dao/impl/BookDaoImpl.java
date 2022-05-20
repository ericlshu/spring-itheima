package com.eric.dao.impl;

import com.eric.dao.BookDao;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Repository
// @Scope("prototype")
public class BookDaoImpl implements BookDao
{
    public void save()
    {
        System.out.println("book dao save ...");
    }

    @PostConstruct // 设置该方法为初始化方法
    public void init()
    {
        System.out.println("book dao init ...");
    }

    @PreDestroy // 设置该方法为销毁方法
    public void destroy()
    {
        System.out.println("book dao destroy ...");
    }
}
