package com.eric.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-18 14:45
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public String getById()
    {
        String result = "spring boot quick start 2 ......";
        System.out.println("result = " + result);
        return result;
    }
}
