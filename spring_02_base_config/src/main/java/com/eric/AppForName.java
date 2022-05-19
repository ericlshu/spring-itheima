package com.eric;

import com.eric.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-05-19 22:28
 * @since jdk-11.0.14
 */
public class AppForName
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        BookService bookService = (BookService) applicationContext.getBean("bookEbi");
        bookService.save();
    }
}
