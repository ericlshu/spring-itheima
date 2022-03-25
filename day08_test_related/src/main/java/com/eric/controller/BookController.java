package com.eric.controller;

import com.eric.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-25 11:14
 */
@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/1")
    public String getById()
    {
        String result = "springboot web test";
        log.warn("result = " + result);
        return result;
    }

    @GetMapping("/2")
    public Book getBook()
    {
        Book book = new Book();
        book.setId(1);
        book.setName("Java编程思想");
        log.warn("book = " + book);
        return book;
    }

}
