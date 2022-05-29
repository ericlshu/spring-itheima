package com.eric;

import com.eric.dao.BookDao;
import com.eric.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Springboot07MybatisApplicationTests
{
    @Resource
    private BookDao bookDao;

    @Test
    void testSave()
    {
        Book book = bookDao.getById(1);
        System.out.println("book = " + book);
    }
}
