package com.eric.controller;

import com.eric.controller.utils.Result;
import com.eric.domain.Book;
import com.eric.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 18:43
 */
@Slf4j
@RestController
@RequestMapping("/books")
public class BookControllerNew {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public Result getAll()
    {
        return new Result(true, bookService.list());
    }

    @PostMapping
    public Result save(@RequestBody Book book)
    {
        log.debug("book = " + book);
        return new Result(bookService.save(book));
    }

    @PutMapping
    public Result update(@RequestBody Book book)
    {
        log.debug("book = " + book);
        return new Result(bookService.updateById(book));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id)
    {
        log.debug("id = " + id);
        return new Result(bookService.removeById(id));
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id)
    {
        log.debug("id = " + id);
        return new Result(true, bookService.getById(id));
    }

    @GetMapping("{current}/{size}")
    public Result getPage(@PathVariable int current, @PathVariable int size)
    {
        log.debug("current = " + current);
        log.debug("size    = " + size);
        return new Result(true, bookService.getPage(current, size));
    }

}
