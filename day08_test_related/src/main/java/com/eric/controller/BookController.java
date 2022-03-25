package com.eric.controller;

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

    @GetMapping
    public String getById()
    {
        String result = "com.eric.controller.BookController.getById ...";
        log.warn("result = " + result);
        return result;
    }

}
