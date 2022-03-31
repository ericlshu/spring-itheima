package com.eric.controller;

import com.eric.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-31 15:13
 */
@RestController
@RequestMapping("/msg")
public class MessageController {

    @Resource
    private MessageService messageService;

    @GetMapping
    private String doSendMessage()
    {
        return messageService.doSendMessage();
    }
}
