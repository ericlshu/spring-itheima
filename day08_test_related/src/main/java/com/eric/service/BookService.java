package com.eric.service;

import com.eric.domain.Book;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-25 12:22
 */
public interface BookService {
    /**
     * save book
     *
     * @param book book
     * @return result
     */
    boolean save(Book book);
}
