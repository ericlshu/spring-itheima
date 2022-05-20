package com.eric;

import com.eric.config.SpringConfig;
import com.eric.dao.BookDao;
import com.eric.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : IOC/DI注解开发
 * <p>
 * 纯注解开发模式
 * 步骤1:创建配置类
 * 步骤2:标识该类为配置类
 * -->> 在配置类上添加`@Configuration`注解，将其标识为一个配置类,替换`applicationContext.xml`
 * 步骤3:用注解替换包扫描配置
 * -->> 在配置类上添加包扫描注解`@ComponentScan`替换`<context:component-scan base-package=""/>`
 * 步骤4:创建运行类并执行
 * -->> new AnnotationConfigApplicationContext(SpringConfig.class);
 *
 * @author Eric L SHU
 * @date 2022-05-20 11:55
 * @since jdk-11.0.14
 */
public class AppForPureAnno
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        BookDao bookDao = (BookDao) context.getBean("bookDao");
        bookDao.save();

        BookService bookService = context.getBean(BookService.class);
        System.out.println("bookService = " + bookService);
        bookService.save();
    }
}
