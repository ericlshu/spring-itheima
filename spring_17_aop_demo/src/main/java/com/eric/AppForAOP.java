package com.eric;

import com.eric.config.SpringConfig;
import com.eric.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : AOP 面向切面编程
 * AOP(Aspect Oriented Programming)面向切面编程，一种编程范式，指导开发者如何组织程序结构。
 * 作用:在不惊动原始设计的基础上为其进行功能增强，前面咱们有技术就可以实现这样的功能即代理模式。
 *
 * @author Eric L SHU
 * @date 2022-05-21 12:15
 * @since jdk-11.0.14
 */
public class AppForAOP
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = context.getBean(BookDao.class);
        bookDao.select();
    }
}
