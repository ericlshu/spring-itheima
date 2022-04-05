package com.eric.app;

import com.eric.config.SpringConfig8;
import com.eric.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : 导入实现了BeanDefinitionRegistryPostProcessor接口的类，通过BeanDefinition的注册器注册实名bean，实现对容器中bean的最终裁定
 *
 * @author Eric L SHU
 * @date 2022-04-04 15:44
 * @since jdk-11.0.14
 */
public class App8
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig8.class);
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.check();
    }
}
