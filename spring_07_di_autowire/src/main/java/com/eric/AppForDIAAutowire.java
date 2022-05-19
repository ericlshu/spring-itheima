package com.eric;

import com.eric.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖自动装配
 * <p>
 * -> IoC容器根据bean所依赖的资源在容器中自动查找并注入到bean中的过程称为自动装配
 * <p>
 * -> 按类型自动装配
 * -->> * 需要注入属性的类中对应属性的setter方法不能省略
 * -->> * 被注入的对象必须要被Spring的IOC容器管理
 * -->> * 按照类型在Spring的IOC容器中如果找到多个对象，会报`NoUniqueBeanDefinitionException
 * <p>
 * -> 一个类型在IOC中有多个对象，还想要注入成功，这个时候就需要按照名称注入
 * <p>
 * -> 按名称自动装配
 * -->> 按照名称注入中的名称指的是什么?
 * -->> * bookDao是private修饰的，外部类无法直接方法
 * -->> * 外部类只能通过属性的set方法进行访问
 * -->> * 对外部类来说，setBookDao方法名，去掉set后首字母小写是其属性名
 * -->> * 为什么是去掉set首字母小写?
 * -->> * 这个规则是set方法生成的默认规则，set方法的生成是把属性名首字母大写前面加set形成的方法名
 * -->> 所以按照名称注入，其实是和对应的set方法有关，但是如果按照标准起名称，属性名和set对应的名是一致的
 * -->> 如果按照名称去找对应的bean对象，找不到则注入Null
 * -->> 当某一个类型在IOC容器中有多个对象，按照名称注入只找其指定名称对应的bean对象，不会报错
 * <p>
 * -> 对于依赖注入，需要注意一些其他的配置特征:
 * <p>
 * -->> 1. 自动装配用于引用类型依赖注入，不能对简单类型进行操作
 * -->> 2. 使用按类型装配时（byType）必须保障容器中相同类型的bean唯一，推荐使用
 * -->> 3. 使用按名称装配时（byName）必须保障容器中具有指定名称的bean，因变量名与配置耦合，不推荐使用
 * -->> 4. 自动装配优先级低于setter注入与构造器注入，同时出现时自动装配配置失效
 */
public class AppForDIAAutowire
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) ctx.getBean("bookService2");
        bookService.save();
    }
}
