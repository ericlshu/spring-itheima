package com.eric.a08;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class MyController
{
    @Lazy
    @Resource
    private BeanForRequest beanForRequest;

    @Lazy
    @Resource
    private BeanForSession beanForSession;

    @Lazy
    @Resource
    private BeanForApplication beanForApplication;

    @GetMapping(value = "/test", produces = "text/html")
    public String test(HttpServletRequest request, HttpSession session)
    {
        ServletContext sc = request.getServletContext();
        return "<ul>" +
                "<li>" + "request scope:" + beanForRequest + "</li>" +
                "<li>" + "session scope:" + beanForSession + "</li>" +
                "<li>" + "application scope:" + beanForApplication + "</li>" +
                "</ul>";
    }
}
