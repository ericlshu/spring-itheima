package com.eric.service.impl;

import com.eric.controller.Code;
import com.eric.dao.BookDao;
import com.eric.domain.Book;
import com.eric.exception.BusinessException;
import com.eric.exception.SystemException;
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
        //模拟业务异常，包装成自定义异常
        if (id == 1)
        {
            throw new BusinessException(Code.BUSINESS_ERR, "请不要使用你的技术挑战我的耐性!");
        }
        //模拟系统异常，将可能出现的异常进行包装，转换成自定义异常
        try
        {
            int i = 1 / 0;
        }
        catch (Exception e)
        {
            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR, "服务器访问超时，请重试!", e);
        }
        return bookDao.getById(id);
    }

    public List<Book> getAll()
    {
        return bookDao.getAll();
    }
}
