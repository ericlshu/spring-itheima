package com.eric.controller;

import com.eric.domain.Book;
import org.springframework.web.bind.annotation.*;

/**
 * -> @ResponseBody配置在类上可以简化配置，表示设置当前每个方法的返回值都作为响应体
 * -> 使用@RestController注解替换@Controller与@ResponseBody注解，简化书写
 */
@RestController
@RequestMapping("/books")
public class BookController
{

    // @RequestMapping( method = RequestMethod.POST)
    // 使用@PostMapping简化Post请求方法对应的映射配置
    @PostMapping
    public String save(@RequestBody Book book)
    {
        System.out.println("book save..." + book);
        return "{'module':'book save'}";
    }

    // @RequestMapping(value = "/{id}" ,method = RequestMethod.DELETE)
    // 使用@DeleteMapping简化DELETE请求方法对应的映射配置
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id)
    {
        System.out.println("book delete..." + id);
        return "{'module':'book delete'}";
    }

    // @RequestMapping(method = RequestMethod.PUT)
    // 使用@PutMapping简化Put请求方法对应的映射配置
    @PutMapping
    public String update(@RequestBody Book book)
    {
        System.out.println("book update..." + book);
        return "{'module':'book update'}";
    }

    // @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    // 使用@GetMapping简化GET请求方法对应的映射配置
    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id)
    {
        System.out.println("book getById..." + id);
        return "{'module':'book getById'}";
    }

    // @RequestMapping(method = RequestMethod.GET)
    // 使用@GetMapping简化GET请求方法对应的映射配置
    @GetMapping
    public String getAll()
    {
        System.out.println("book getAll...");
        return "{'module':'book getAll'}";
    }
}
