package com.eric.service;

import com.eric.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookService
{
    /**
     * 保存
     */
    boolean save(Book book);

    /**
     * 修改
     */
    boolean update(Book book);

    /**
     * 按id删除
     */
    boolean delete(Integer id);

    /**
     * 按id查询
     */
    Book getById(Integer id);

    /**
     * 查询全部
     */
    List<Book> getAll();
}
