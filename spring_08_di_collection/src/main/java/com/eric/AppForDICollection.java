package com.eric;

import com.eric.dao.BookDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description : 集合类型注入
 * property标签表示setter方式注入，构造方式注入constructor-arg标签内部也可以写<array>、<list>、<set>、<map>、<props>标签
 * List的底层也是通过数组实现的，所以<list>和<array>标签是可以混用
 * 集合中要添加引用类型，只需要把<value>标签改成<ref>标签，这种方式用的比较少·
 *
 * @author Eric L SHU
 * @date 2022-05-20 0:43
 * @since jdk-11.0.14
 */
public class AppForDICollection
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();
    }
}
