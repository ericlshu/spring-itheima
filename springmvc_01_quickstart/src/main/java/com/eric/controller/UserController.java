package com.eric.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description :
 *
 * @author Eric SHU
 */
@Controller
public class UserController
{
    @RequestMapping("/save")
    @ResponseBody
    public String save()
    {
        System.out.println("com.eric.controller.UserController.save ...");
        return "{'info':'SpringMVC'}";
    }
}
