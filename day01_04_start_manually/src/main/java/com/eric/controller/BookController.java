package com.eric.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-18 16:59
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public String test()
    {
        String result = "spring boot quick start 4 ......";
        System.out.println("result = " + result);
        return result;
    }
}
