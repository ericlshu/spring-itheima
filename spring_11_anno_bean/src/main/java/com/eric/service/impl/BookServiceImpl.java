package com.eric.service.impl;

import com.eric.dao.BookDao;
import com.eric.service.BookService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BookServiceImpl implements BookService
{
    @Resource
    private BookDao bookDao;

    public void save()
    {
        System.out.println("book service save ...");
        bookDao.save();
    }
}
