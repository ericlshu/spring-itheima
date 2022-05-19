package com.eric.service.impl;

import com.eric.dao.BookDao;
import com.eric.service.BookService;

public class BookServiceImpl implements BookService
{
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao)
    {
        this.bookDao = bookDao;
    }

    public void save()
    {
        System.out.println("book service save ...");
        bookDao.save();
    }
}
