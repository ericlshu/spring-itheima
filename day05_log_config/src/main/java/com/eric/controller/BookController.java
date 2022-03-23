package com.eric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public String getById()
    {
        String result = "spring boot log config ......";
        LOGGER.info("result = " + result);

        LOGGER.trace("trace message ......");
        LOGGER.debug("debug message ......");
        LOGGER.info("info message ......");
        LOGGER.warn("warn message ......");
        LOGGER.error("error message ......");

        return result;
    }
}
