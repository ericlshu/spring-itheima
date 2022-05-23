package com.eric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController
{
    @RequestMapping("/commonParam")
    @ResponseBody
    public String commonParam(String name, int age)
    {
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return "{'module':'commonParam'}";
    }
}
