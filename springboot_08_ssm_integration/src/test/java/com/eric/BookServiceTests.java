package com.eric;

import com.eric.domain.Book;
import com.eric.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookServiceTests
{
    @Autowired
    private BookService bookService;

    @Test
    public void testGetById()
    {
        Book book = bookService.getById(2);
        System.out.println(book);
    }

    @Test
    public void testGetAll()
    {
        List<Book> books = bookService.getAll();
        books.forEach(System.out::println);
    }
}
