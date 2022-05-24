package com.eric.service.impl;

import com.eric.dao.BookDao;
import com.eric.domain.Book;
import com.eric.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService
{
    @Resource
    private BookDao bookDao;

    public boolean save(Book book)
    {
        bookDao.save(book);
        return true;
    }

    public boolean update(Book book)
    {
        bookDao.update(book);
        return true;
    }

    public boolean delete(Integer id)
    {
        bookDao.delete(id);
        return true;
    }

    public Book getById(Integer id)
    {
        return bookDao.getById(id);
    }

    public List<Book> getAll()
    {
        return bookDao.getAll();
    }
}
