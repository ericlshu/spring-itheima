package com.eric.service.impl;

import com.eric.domain.Book;
import com.eric.mapper.BookMapper;
import com.eric.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 18:22
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource(name = "bookMapper")
    private BookMapper bookMapper;

    private final Map<Integer, Book> cache = new HashMap<>();

    @Override
    public boolean save(Book book)
    {
        return bookMapper.insert(book) > 0;
    }

    @Override
    public Book getById(Integer id)
    {
        //如果当前缓存中没有本次要查询的数据，则进行查询，否则直接从缓存中获取数据返回
        Book book = cache.get(id);
        if (book == null)
        {
            book = bookMapper.selectById(id);
            cache.put(id,book);
        }
        return book;
    }

    @Override
    public boolean update(Book book)
    {
        return bookMapper.updateById(book) > 0;
    }

    @Override
    public boolean delete(Integer id)
    {
        return bookMapper.deleteById(id) > 0;
    }

    @Override
    public List<Book> getAll()
    {
        return bookMapper.selectList(null);
    }
}
