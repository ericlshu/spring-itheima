package com.eric;

import com.eric.config.SpringConfig;
import com.eric.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : AOP切入点表达式
 *
 * @author Eric L SHU
 */
public class AppForPointCut
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = context.getBean(BookDao.class);
        bookDao.update();
    }
}
