package com.eric.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.eric.controller.utils.Result;
import com.eric.domain.Book;
import com.eric.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-20 18:43
 */
@Slf4j
@RestController
@RequestMapping("/books")
public class BookControllerNew
{

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
    public Result update(@RequestBody Book book) throws IOException
    {
        if ("123".equals(book.getName()))
        {
            throw new IOException();
        }
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

    // @GetMapping("{current}/{size}")
    public Result getPage(@PathVariable int current, @PathVariable int size)
    {
        log.debug("current = " + current);
        log.debug("size    = " + size);
        IPage<Book> page = bookService.getPage(current, size);
        // 如果当前页码值大于总页码值，重新执行查询，使用最大页码值作为当前页码值
        if (current > page.getPages())
            page = bookService.getPage((int) page.getPages(), size);
        return new Result(true, page);
    }

    // @Resource
    // private IpCountService ipCountService;

    @GetMapping("{current}/{size}")
    public Result getPage(@PathVariable int current, @PathVariable int size, Book book)
    {
        log.debug("current = " + current);
        log.debug("size    = " + size);
        log.debug("book = " + book);

        // ipCountService.count();

        IPage<Book> page = bookService.getPage(current, size, book);
        // 如果当前页码值大于总页码值，重新执行查询，使用最大页码值作为当前页码值
        if (current > page.getPages())
            page = bookService.getPage((int) page.getPages(), size, book);
        return new Result(true, page);
    }
}
