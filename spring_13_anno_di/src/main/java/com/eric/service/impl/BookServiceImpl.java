package com.eric.service.impl;

import com.eric.dao.BookDao;
import com.eric.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService
{
    @Autowired
    @Qualifier("bookDao")
    private BookDao bookDao;

    /*public void setBookDao(BookDao bookDao)
    {
        this.bookDao = bookDao;
    }*/

    @Value("${jdbc.url}")
    String url;
    @Value("${jdbc.driver}")
    String driver;
    @Value("${jdbc.username}")
    String username;
    @Value("${jdbc.password}")
    String password;

    @Value("eric666")
    String name;

    public void save()
    {
        System.out.println("book service save ...");
        System.out.println("name = " + name);
        System.out.println("url = " + url);
        System.out.println("driver = " + driver);
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        bookDao.save();
    }
}
