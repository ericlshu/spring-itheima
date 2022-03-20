package com.eric.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eric.domain.Book;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 18:06
 */
public interface BookService {

    /**
     * save book
     *
     * @param book book
     * @return true : save successfully; false: save failed
     */
    Boolean save(Book book);

    /**
     * update book
     *
     * @param book book to be updated
     * @return true : update successfully; false: update failed
     */
    Boolean update(Book book);

    /**
     * delete book by id
     *
     * @param id book id
     * @return true : delete successfully; false: delete failed
     */
    Boolean delete(Integer id);

    /**
     * find book by book id
     *
     * @param id book id
     * @return book
     */
    Book getById(Integer id);

    /**
     * find All book
     *
     * @return book list
     */
    List<Book> getAll();

    /**
     * get book by page
     *
     * @param current current page
     * @param size    page size
     * @return books by page
     */
    IPage<Book> getBooksByPage(int current, int size);
}
