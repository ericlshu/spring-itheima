package com.eric.service.impl;

import com.eric.dao.BookDao;
import com.eric.dao.UserDao;
import com.eric.service.BookService;

public class BookServiceImpl implements BookService
{
    private final BookDao bookDao;
    private final UserDao userDao;

    public BookServiceImpl(BookDao bookDao, UserDao userDao)
    {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    public void save()
    {
        System.out.println("book service save ...");
        bookDao.save();
        userDao.save();
    }
}
