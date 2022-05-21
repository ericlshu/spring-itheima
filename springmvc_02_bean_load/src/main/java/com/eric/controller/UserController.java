package com.eric.controller;

import com.eric.domain.User;
import com.eric.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller()
@RequestMapping("/user")
public class UserController
{
    @Resource
    private UserService userService;

    @RequestMapping("/save")
    @ResponseBody
    public String save()
    {
        System.out.println("user save ...");
        return "{'info':'springmvc'}";
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public String findById(@PathVariable("id") Integer id)
    {
        System.out.println("user findById ...");
        User user = userService.findById(id);
        return user.toString();
    }
}
