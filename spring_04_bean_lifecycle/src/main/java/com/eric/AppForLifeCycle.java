package com.eric;

import com.eric.dao.BookDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description : bean生命周期
 *
 * (1)关于Spring中对bean生命周期控制提供了两种方式:
 *
 * * 在配置文件中的bean标签中添加`init-method`和`destroy-method`属性
 * * 类实现`InitializingBean`与`DisposableBean`接口，这种方式了解下即可。
 *
 * (2)对于bean的生命周期控制在bean的整个生命周期中所处的位置如下:
 *
 * * 初始化容器
 *   * 1.创建对象(内存分配)
 *   * 2.执行构造方法
 *   * 3.执行属性注入(set操作)
 *   * 4.执行bean初始化方法
 * * 使用bean
 *   * 1.执行业务操作
 * * 关闭/销毁容器
 *   * 1.执行bean销毁方法
 *
 * (3)关闭容器的两种方式:
 *
 * * ConfigurableApplicationContext是ApplicationContext的子类
 *   * close()方法
 *   * registerShutdownHook()方法
 *
 * @author Eric L SHU
 * @date 2022-05-19 22:28
 * @since jdk-11.0.14
 */
public class AppForLifeCycle
{
    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookDao bookDao = (BookDao) ctx.getBean("bookDao");
        bookDao.save();

        // Spring的IOC容器是运行在JVM中
        // 运行main方法后,JVM启动,Spring加载配置文件生成IOC容器,从容器获取bean对象，然后调方法执行
        // main方法执行完后，JVM退出，这个时候IOC容器中的bean还没有来得及销毁就已经结束了
        // 所以没有调用对应的destroy方法

        // 关闭容器，在调用的时候关闭
        // ctx.close();

        // 在容器未关闭之前，提前设置好回调函数，让JVM在退出之前回调此函数来关闭容器
        ctx.registerShutdownHook();
    }
}
