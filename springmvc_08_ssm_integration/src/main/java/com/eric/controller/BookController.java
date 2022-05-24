package com.eric.controller;

import com.eric.domain.Book;
import com.eric.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController
{
    @Resource
    private BookService bookService;

    @PostMapping
    public boolean save(@RequestBody Book book)
    {
        return bookService.save(book);
    }

    @PutMapping
    public boolean update(@RequestBody Book book)
    {
        return bookService.update(book);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id)
    {
        return bookService.delete(id);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id)
    {
        return bookService.getById(id);
    }

    @GetMapping
    public List<Book> getAll()
    {
        return bookService.getAll();
    }
}
