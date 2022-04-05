package com.eric.service.impl;

import com.eric.service.BookService;
import org.springframework.stereotype.Service;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-04 12:52
 * @since jdk-11.0.14
 */
@Service("bookService")
public class BookServiceImpl1 implements BookService
{
    @Override
    public void check()
    {
        System.out.println("book service 1 ..");
    }
}
