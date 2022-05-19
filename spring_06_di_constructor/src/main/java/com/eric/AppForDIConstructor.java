package com.eric;

import com.eric.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用构造器进行属性注入
 * 1. 强制依赖使用构造器进行，使用setter注入有概率不进行注入导致null对象出现
 *    * 强制依赖指对象在创建的过程中必须要注入指定的参数
 * 2. 可选依赖使用setter注入进行，灵活性强
 *    * 可选依赖指对象在创建过程中注入的参数可有可无
 * 3. Spring框架倡导使用构造器，第三方框架内部大多数采用构造器注入的形式进行数据初始化，相对严谨
 * 4. 如果有必要可以两者同时使用，使用构造器注入完成强制依赖的注入，使用setter注入完成可选依赖的注入
 * 5. 实际开发过程中还要根据实际情况分析，如果受控对象没有提供setter方法就必须使用构造器注入
 * 6. 自己开发的模块推荐使用setter注入
 */
public class AppForDIConstructor
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) ctx.getBean("bookService");
        bookService.save();
    }
}
