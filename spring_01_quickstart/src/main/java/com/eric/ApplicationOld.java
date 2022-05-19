package com.eric;

import com.eric.service.BookService;
import com.eric.service.impl.BookServiceImpl;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-05-19 22:24
 * @since jdk-11.0.14
 */
public class ApplicationOld
{
    public static void main(String[] args)
    {
        BookService bookService = new BookServiceImpl();
        bookService.save();
    }
}
