package com.eric;

import com.eric.dao.impl.BookDaoImpl;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

/**
 * Description : 加载第三方bean并使用配置文件
 * <p>
 * 步骤1:准备properties配置文件
 * 步骤2:开启context命名空间
 * 步骤3:加载properties配置文件
 * -->> <context:property-placeholder location="jdbc.properties"/>
 * 步骤4:完成属性注入
 * -->> 使用${key}来读取properties配置文件中的内容并完成属性注入
 * <p>
 * <context:property-placeholder/>标签会加载系统的环境变量，而且环境变量的值会被优先加载
 * system-properties-mode:设置为NEVER,表示不加载系统属性;
 *
 * @author Eric L SHU
 * @date 2022-05-20 10:16
 * @since jdk-11.0.14
 */
public class AppForDatasource
{
    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        DataSource druidDataSource = (DataSource) applicationContext.getBean("druidDataSource");
        System.out.println("druidDataSource = " + druidDataSource);

        ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource) applicationContext.getBean("comboPooledDataSource");
        System.out.println("comboPooledDataSource = " + comboPooledDataSource);

        BookDaoImpl bookDao = applicationContext.getBean(BookDaoImpl.class);
        bookDao.save();
    }
}
