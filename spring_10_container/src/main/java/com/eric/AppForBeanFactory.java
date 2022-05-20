package com.eric;

import com.eric.dao.BookDao;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Description : BeanFactory和ApplicationContext之间的区别
 * <p>
 * BeanFactory是延迟加载，只有在获取bean对象的时候才会去创建
 * ApplicationContext是立即加载，容器加载的时候就会创建bean对象
 * ApplicationContext要想成为延迟加载，只需要在bean定义时添加lazy-init="true"属性
 *
 * @author Eric L SHU
 * @date 2022-05-20 10:16
 * @since jdk-11.0.14
 */
public class AppForBeanFactory
{
    public static void main(String[] args)
    {
        Resource resource = new ClassPathResource("applicationContext.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        BookDao bookDao = beanFactory.getBean(BookDao.class);
        bookDao.save();
    }
}
