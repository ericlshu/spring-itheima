package com.eric;

import com.eric.config.SpringConfig;
import com.eric.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description : 注解开发依赖注入
 * <p>
 * 注解实现按照类型注入
 * -> @Autowired可以写在属性上，也可也写在setter方法上，最简单的处理方式是`写在属性上并将setter方法删除掉`
 * -> 为什么setter方法可以删除呢?
 * -->> 自动装配基于反射设计创建对象并通过暴力反射为私有属性进行设值
 * -->> 普通反射只能获取public修饰的内容
 * -->> 暴力反射除了获取public修饰的内容还可以获取private修改的内容
 * -->> 所以此处无需提供setter方法
 * <p>
 * 注解实现按照名称注入
 * -> @Qualifier注解后的值就是需要注入的bean的名称。
 * ->> 注意:@Qualifier不能独立使用，必须和@Autowired一起使用
 * <p>
 * 简单数据类型注入
 * ->> 使用@Value注解，将值写入注解的参数中
 * <p>
 * 注解读取properties配置文件
 * -> 步骤1: resource下准备properties文件
 * -> 步骤2: 使用注解加载properties配置文件
 * -> 步骤3: 使用@Value读取配置文件中的内容
 * -> 如果读取的properties配置文件有多个，可以使用`@PropertySource`的属性来指定多个
 * -->> @PropertySource({"jdbc.properties","xxx.properties"})
 * -> @PropertySource`注解属性中不支持使用通配符`*`,运行会报错
 * -->> @PropertySource({"*.properties"})
 * -> @PropertySource`注解属性中可以把`classpath:`加上,代表从当前项目的根路径找文件
 * -->> @PropertySource({"classpath:jdbc.properties"})
 *
 * @author Eric L SHU
 * @date 2022-05-20 14:59
 * @since jdk-11.0.14
 */
public class AppForAnnoDI
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookService bookService = context.getBean(BookService.class);
        bookService.save();
    }
}
