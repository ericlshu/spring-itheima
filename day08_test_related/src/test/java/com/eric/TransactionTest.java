package com.eric;

import com.eric.domain.Book;
import com.eric.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-25 12:27
 */
@Slf4j
@SpringBootTest
@Transactional
@Rollback
public class TransactionTest {

    @Autowired
    private BookService bookService;

    @Test
    void testSave()
    {
        Book book = new Book();
        book.setName("Java编程思想（第6版）");
        book.setDescription("Java学习必读经典,殿堂级著作！赢得了全球程序员的广泛赞誉。");
        book.setType("编程技术");
        boolean result = bookService.save(book);
        log.warn("result : " + result);
    }
}
