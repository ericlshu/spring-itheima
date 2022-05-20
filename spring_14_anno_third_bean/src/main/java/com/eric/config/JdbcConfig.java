package com.eric.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.eric.dao.BookDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Description : 注入引用数据类型步骤
 * <p>
 * 步骤1:在SpringConfig中扫描BookDao
 * 步骤2:在JdbcConfig类的方法上添加参数
 * -> 引用类型注入只需要为bean定义方法设置形参即可，容器会根据类型自动装配对象。
 *
 * @author Eric L SHU
 * @date 2022-05-20 15:42
 * @since jdk-11.0.14
 */
// @Configuration
public class JdbcConfig
{
    @Value("${jdbc.url}")
    String url;
    @Value("${jdbc.driver}")
    String driver;
    @Value("${jdbc.username}")
    String username;
    @Value("${jdbc.password}")
    String password;

    @Bean
    public DataSource dataSource(BookDao bookDao)
    {
        System.out.println("bookDao = " + bookDao);
        bookDao.save();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
