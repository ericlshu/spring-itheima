package com.eric.controller;

import com.eric.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 18:31
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("{number}")
    public String get(@PathVariable String number)
    {
        return messageService.getCode(number);
    }

    @PostMapping
    public boolean check(String number, String code)
    {
        return messageService.check(number, code);
    }
}
