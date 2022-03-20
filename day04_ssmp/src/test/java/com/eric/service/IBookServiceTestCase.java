package com.eric.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eric.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 18:15
 */
@Slf4j
@SpringBootTest
public class IBookServiceTestCase {

    @Autowired
    private IBookService bookService;

    @Test
    void testGetById()
    {
        System.out.println(bookService.getById(1));
    }

    @Test
    void testGetAll()
    {
        List<Book> bookList = bookService.list();
        for (Book book : bookList)
        {
            System.out.println(book);
        }
    }

    @Test
    void testSave()
    {
        Book book = new Book();
        book.setType("计算机理论");
        book.setName("Java编程思想1");
        book.setDescription("Java学习必读经典，殿堂级著作！赢得了全球程序员的广泛赞誉。");

        boolean result = bookService.save(book);
        log.info("result : " + result);
        log.info("book : " + book);
    }

    @Test
    void testDelete()
    {
        boolean result = bookService.removeById(30);
        log.info("result : " + result);
    }

    @Test
    void testGetPage()
    {
        IPage<Book> page = new Page<>(2, 5);
        page = bookService.page(page);

        List<Book> bookList = page.getRecords();
        for (Book book : bookList)
        {
            System.out.println(book);
        }
    }
}
