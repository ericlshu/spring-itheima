package com.eric.controller;

import com.eric.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 关于接收参数三个注解`@RequestBody`、`@RequestParam`、`@PathVariable`,这三个注解之间的区别和应用分别是什么?
 * -> 区别
 * --->> @RequestParam用于接收url地址传参或表单传参
 * --->> @RequestBody用于接收json数据
 * --->> @PathVariable用于接收路径参数，使用{参数名称}描述路径参数
 * -> 应用
 * --->> 后期开发中，发送请求参数超过1个时，以json格式为主，@RequestBody应用较广
 * --->> 如果发送非json格式数据，选用@RequestParam接收请求参数
 * --->> 采用RESTful进行开发，当参数数量较少时，例如1个，可以采用@PathVariable接收请求路径变量，通常用于传递id值
 */
@Controller
public class UserController
{
    /**
     * 设置当前请求方法为POST，表示REST风格中的添加操作
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public String save()
    {
        System.out.println("user save...");
        return "{'module':'user save'}";
    }

    /**
     * 设置当前请求方法为DELETE，表示REST风格中的删除操作
     * -> @PathVariable注解用于设置路径变量（路径参数），要求路径上设置对应的占位符，并且占位符名称与方法形参名称相同
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable Integer id)
    {
        System.out.println("user delete..." + id);
        return "{'module':'user delete'}";
    }

    /**
     * 设置当前请求方法为PUT，表示REST风格中的修改操作
     */
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody User user)
    {
        System.out.println("user update..." + user);
        return "{'module':'user update'}";
    }

    /**
     * 设置当前请求方法为GET，表示REST风格中的查询操作
     * -> @PathVariable注解用于设置路径变量（路径参数），要求路径上设置对应的占位符，并且占位符名称与方法形参名称相同
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getById(@PathVariable Integer id)
    {
        System.out.println("user getById..." + id);
        return "{'module':'user getById'}";
    }

    /**
     * 设置当前请求方法为GET，表示REST风格中的查询操作
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public String getAll()
    {
        System.out.println("user getAll...");
        return "{'module':'user getAll'}";
    }
}
