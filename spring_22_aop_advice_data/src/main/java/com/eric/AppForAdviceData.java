package com.eric;

import com.eric.config.SpringConfig;
import com.eric.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description :
 *
 * @author Eric SHU
 */
public class AppForAdviceData
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = ctx.getBean(BookDao.class);
        String name = bookDao.findName(10, "eric");
        System.out.println(name);
    }
}
