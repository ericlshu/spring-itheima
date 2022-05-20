package com.eric.dao.impl;

import com.eric.dao.BookDao;

public class BookDaoImpl implements BookDao
{
    public BookDaoImpl()
    {
        System.out.println("book dao constructor ...");
    }

    public void save()
    {
        System.out.println("book dao save ...");
    }
}
