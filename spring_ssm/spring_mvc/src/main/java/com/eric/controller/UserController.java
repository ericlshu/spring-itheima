package com.eric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-08 13:33
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/quick")
    public String save()
    {
        System.out.println("save user in controller ...");
        return "success";
    }
}
