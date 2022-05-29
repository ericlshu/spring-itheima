package com.eric.controller;

import com.eric.domain.Enterprise;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/books")
public class BookController
{
    @Value("${lesson}")
    private String lesson;
    @Value("${server.port}")
    private Integer port;
    @Value("${enterprise.subject[0]}")
    private String subject_00;

    @Resource
    private Environment environment;

    @Resource
    private Enterprise enterprise;

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id)
    {
        // 1.使用 @Value注解
        System.out.println("id ==> " + id);
        System.out.println("lesson = " + lesson);
        System.out.println("port = " + port);
        System.out.println("subject_00 = " + subject_00);
        System.out.println("-------------------------------------------------");
        // 2.Environment对象
        System.out.println(environment.getProperty("lesson"));
        System.out.println(environment.getProperty("enterprise.name"));
        System.out.println(environment.getProperty("enterprise.age"));
        System.out.println(environment.getProperty("enterprise.subject[1]"));
        System.out.println("-------------------------------------------------");
        // 3. 自定义对象
        System.out.println("enterprise = " + enterprise);
        return "hello , spring boot!";
    }
}
