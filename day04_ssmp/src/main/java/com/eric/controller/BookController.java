package com.eric.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eric.domain.Book;
import com.eric.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 18:43
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public List<Book> getAll()
    {
        return bookService.list();
    }

    @PostMapping
    public boolean save(@RequestBody Book book)
    {
        System.out.println("book = " + book);
        return bookService.save(book);
    }

    @PutMapping
    public boolean update(@RequestBody Book book)
    {
        System.out.println("book = " + book);
        return bookService.updateById(book);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id)
    {
        return bookService.removeById(id);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id)
    {
        return bookService.getById(id);
    }

    @GetMapping("{current}/{size}")
    public IPage<Book> getPage(@PathVariable int current, @PathVariable int size)
    {
        return bookService.getPage(current, size);
    }

}
