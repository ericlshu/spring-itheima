package com.eric.service.impl;

import com.eric.dao.BookDao;
import com.eric.dao.UserDao;
import com.eric.service.BookService;

public class BookServiceImpl implements BookService
{
    private BookDao bookDao;
    private UserDao userDao;

    //setter注入需要提供要注入对象的set方法
    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    //setter注入需要提供要注入对象的set方法
    public void setBookDao(BookDao bookDao)
    {
        this.bookDao = bookDao;
    }

    public void save()
    {
        System.out.println("book service save ...");
        bookDao.save();
        userDao.save();
    }
}
