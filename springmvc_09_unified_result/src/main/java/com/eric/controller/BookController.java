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
    public Result save(@RequestBody Book book)
    {
        boolean save = bookService.save(book);
        return new Result(save ? Code.SAVE_OK : Code.SAVE_ERR, save);
    }

    @PutMapping
    public Result update(@RequestBody Book book)
    {
        boolean update = bookService.update(book);
        return new Result(update ? Code.UPDATE_OK : Code.UPDATE_ERR, update);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id)
    {
        boolean delete = bookService.delete(id);

        return new Result(delete ? Code.DELETE_OK : Code.DELETE_ERR, delete);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id)
    {
        Book book = bookService.getById(id);
        int code = (book != null) ? Code.GET_OK : Code.GET_ERR;
        String msg = (book != null) ? "" : "查询失败，请重试！";
        return new Result(code, book, msg);
    }

    @GetMapping
    public Result getAll()
    {
        List<Book> bookList = bookService.getAll();
        int code = (bookList == null || bookList.isEmpty()) ? Code.GET_ERR : Code.GET_OK;
        String msg = (bookList == null || bookList.isEmpty()) ? "查询失败，请重试！" : "";
        return new Result(code, bookList, msg);
    }
}
