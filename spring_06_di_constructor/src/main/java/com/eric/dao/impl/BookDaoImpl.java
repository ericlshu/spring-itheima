package com.eric.dao.impl;

import com.eric.dao.BookDao;

public class BookDaoImpl implements BookDao
{
    private final String databaseName;
    private final int connectionNum;

    public BookDaoImpl(String databaseName, int connectionNum)
    {
        this.databaseName = databaseName;
        this.connectionNum = connectionNum;
    }

    public void save()
    {
        System.out.println("book dao save ...");
        System.out.println("databaseName = " + databaseName);
        System.out.println("connectionNum = " + connectionNum);
    }
}
