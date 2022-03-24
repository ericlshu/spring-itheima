package com.eric.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eric.domain.Book;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 18:26
 */
public interface IBookService extends IService<Book> {

    /**
     * get book list by page
     *
     * @param current current page
     * @param size    page size
     * @return book list
     */
    IPage<Book> getPage(int current, int size);

    /**
     * get book list by page with condition
     *
     * @param current current page
     * @param size    page size
     * @param book    query condition
     * @return book list
     */
    IPage<Book> getPage(int current, int size, Book book);
}
