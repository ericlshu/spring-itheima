package com.eric.dao.impl;

import com.eric.dao.BookDao;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao
{
    public String findName(int id, String password)
    {
        System.out.println("id:" + id);
        int i = 1 / 0;
        return password;
    }
}
