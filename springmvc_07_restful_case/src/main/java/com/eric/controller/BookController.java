package com.eric.controller;

import com.eric.domain.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author Eric SHU
 */
@RestController
@RequestMapping("/books")
public class BookController
{
    @PostMapping
    public String save(@RequestBody Book book)
    {
        System.out.println("book = " + book);
        return "{'module':'book save success'}";
    }

    @GetMapping
    public List<Book> getAll()
    {
        List<Book> bookList = new ArrayList<>();

        Book book1 = new Book();
        book1.setType("计算机");
        book1.setName("SpringMVC入门教程");
        book1.setDescription("小试牛刀");
        bookList.add(book1);

        Book book2 = new Book();
        book2.setType("计算机");
        book2.setName("SpringMVC实战教程");
        book2.setDescription("一代宗师");
        bookList.add(book2);

        Book book3 = new Book();
        book3.setType("计算机丛书");
        book3.setName("SpringMVC实战教程进阶");
        book3.setDescription("一代宗师呕心创作");
        bookList.add(book3);

        return bookList;
    }
}
