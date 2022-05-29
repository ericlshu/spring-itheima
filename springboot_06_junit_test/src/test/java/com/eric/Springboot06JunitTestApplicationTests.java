package com.eric;

import com.eric.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Springboot06JunitTestApplicationTests
{
    @Resource
    private BookService bookService;

    @Test
    void save()
    {
        bookService.save();
    }
}
