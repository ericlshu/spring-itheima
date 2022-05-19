package com.eric;

import com.eric.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 步骤1:声明属性并提供setter方法
 * 步骤2:配置文件中进行注入配置
 * * 对于引用数据类型使用的是`<property name="" ref=""/>
 * * 对于简单数据类型使用的是`<property name="" value=""/>
 */
public class AppForDISetter
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) ctx.getBean("bookService");
        bookService.save();
    }
}
