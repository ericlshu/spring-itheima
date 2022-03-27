package com.eric.service;

import com.eric.domain.Book;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 18:20
 */
public interface BookService {
    /**
     * save book
     *
     * @param book book
     * @return save result
     */
    boolean save(Book book);

    /**
     * get book by id
     *
     * @param id book id
     * @return book
     */
    Book getById(Integer id);

    /**
     * update book
     *
     * @param book book
     * @return update result
     */
    boolean update(Book book);

    /**
     * delete book by id
     *
     * @param id book id
     * @return delete result
     */
    boolean delete(Integer id);

    /**
     * get all book
     *
     * @return book list
     */
    List<Book> getAll();
}
