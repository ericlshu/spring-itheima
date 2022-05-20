package com.eric;

import com.eric.dao.BookDao;
import com.eric.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description : IOC/DI注解开发
 * <p>
 * 注解开发定义bean
 * -->> 步骤1:删除原XML配置，将配置文件中的`<bean>`标签删除掉
 * -->> 步骤2:Dao上添加注解，在BookDaoImpl类上添加`@Component`注解
 * -->> 步骤3:配置Spring的注解包扫描
 * -->> 步骤4:运行程序
 *
 * @author Eric L SHU
 * @date 2022-05-20 11:55
 * @since jdk-11.0.14
 */
public class AppForAnnotation
{
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        BookDao bookDao = (BookDao) context.getBean("bookDao");
        bookDao.save();

        BookService bookService = context.getBean(BookService.class);
        System.out.println("bookService = " + bookService);
        bookService.save();
    }
}
