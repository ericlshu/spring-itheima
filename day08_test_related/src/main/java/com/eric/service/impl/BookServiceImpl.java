package com.eric.service.impl;

import com.eric.domain.Book;
import com.eric.mapper.BookMapper;
import com.eric.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-25 12:23
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource(name = "bookMapper")
    private BookMapper bookMapper;

    @Override
    public boolean save(Book book)
    {
        return bookMapper.insert(book) > 0;
    }
}
