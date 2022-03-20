package com.eric.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eric.domain.Book;
import com.eric.mapper.BookMapper;
import com.eric.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 18:11
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource(name = "bookMapper")
    private BookMapper bookMapper;

    @Override
    public Boolean save(Book book)
    {
        return bookMapper.insert(book) > 0;
    }

    @Override
    public Boolean update(Book book)
    {
        return bookMapper.updateById(book) > 0;
    }

    @Override
    public Boolean delete(Integer id)
    {
        return bookMapper.deleteById(id) > 0;
    }

    @Override
    public Book getById(Integer id)
    {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> getAll()
    {
        return bookMapper.selectList(null);
    }

    @Override
    public IPage<Book> getBooksByPage(int current, int size)
    {
        return bookMapper.selectPage(new Page<>(current, size), null);
    }
}
