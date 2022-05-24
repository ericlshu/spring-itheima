package com.eric;

import com.eric.config.SpringConfig;
import com.eric.domain.Book;
import com.eric.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class BookServiceTest
{
    @Resource
    private BookService bookService;

    @Test
    public void testGetById()
    {
        Book book = bookService.getById(1);
        System.out.println(book);
    }

    @Test
    public void testGetAll()
    {
        List<Book> bookList = bookService.getAll();
        bookList.forEach(System.out::println);
    }
}
