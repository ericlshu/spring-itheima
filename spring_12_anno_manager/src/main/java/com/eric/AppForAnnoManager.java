package com.eric;

import com.eric.config.SpringConfig;
import com.eric.dao.BookDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-05-20 14:59
 * @since jdk-11.0.14
 */
public class AppForAnnoManager
{
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao1 = context.getBean(BookDao.class);
        BookDao bookDao2 = context.getBean(BookDao.class);
        System.out.println("bookDao1 = " + bookDao1);
        System.out.println("bookDao2 = " + bookDao2);
        bookDao1.save();
        bookDao2.save();
        context.close();
    }
}
