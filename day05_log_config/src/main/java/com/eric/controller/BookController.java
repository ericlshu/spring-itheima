package com.eric.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-18 14:45
 */
@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    // public static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public String getById()
    {
        String result = "spring boot log config ......";
        log.info("result = " + result);

        log.trace("trace message ......");
        log.debug("debug message ......");
        log.info("info message ......");
        log.warn("warn message ......");
        log.error("error message ......");

        return result;
    }
}
