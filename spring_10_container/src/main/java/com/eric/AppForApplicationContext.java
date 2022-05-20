package com.eric;

import com.eric.dao.BookDao;
import com.eric.dao.impl.BookDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description :
 *
 * 容器创建的两种方式
 * -> ClassPathXmlApplicationContext[掌握]
 * -> FileSystemXmlApplicationContext[知道即可]
 *
 * 获取Bean的三种方式
 * -> getBean("名称"):需要类型转换
 * -> getBean("名称",类型.class):多了一个参数
 * -> getBean(类型.class):容器中不能有多个该类的bean对象
 *
 * 容器类层次结构
 * -->> 只需要知晓容器的最上级的父接口为BeanFactory即可
 *
 * BeanFactory
 * -> 使用BeanFactory创建的容器是延迟加载
 * -> 使用ApplicationContext创建的容器是立即加载
 * -> 具体BeanFactory如何创建只需要了解即可。
 *
 * @author Eric L SHU
 * @date 2022-05-20 10:16
 * @since jdk-11.0.14
 */
public class AppForApplicationContext
{
    public static void main(String[] args)
    {
        // 1.加载类路径下的配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 2.从文件系统下加载配置文件,当项目的位置发生变化后,代码也需要跟着改,耦合度较高,不推荐使用。
        // ApplicationContext context = new FileSystemXmlApplicationContext(
        //         "D:\\Sandbox\\Workspace\\IntelliJIdea\\spring-projects\\spring_10_container\\src\\main\\resources\\applicationContext.xml");

        BookDao bookDao1 = context.getBean(BookDaoImpl.class);
        bookDao1.save();

        BookDao bookDao2 = context.getBean("bookDao", BookDao.class);
        bookDao2.save();

        // 必须要确保IOC容器中该类型对应的bean对象只能有一个
        BookDao bookDao3 = context.getBean(BookDao.class);
        bookDao3.save();
    }
}
