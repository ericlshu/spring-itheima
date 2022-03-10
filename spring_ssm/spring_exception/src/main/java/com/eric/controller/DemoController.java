package com.eric.controller;

import com.eric.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-10 20:29
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/show")
    public String show(@RequestParam(value = "name", required = false) String name)
    {
        System.out.println("show running ...");
        demoService.show1();
        return "index";
    }
}
