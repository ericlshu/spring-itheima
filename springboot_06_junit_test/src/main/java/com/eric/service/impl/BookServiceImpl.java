package com.eric.service.impl;

import com.eric.service.BookService;
import org.springframework.stereotype.Service;

/**
 * Description :
 *
 * @author Eric SHU
 */
@Service
public class BookServiceImpl implements BookService
{
    @Override
    public void save()
    {
        System.out.println("book service is running ...");
    }
}
