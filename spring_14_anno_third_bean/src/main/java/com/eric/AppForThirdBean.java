package com.eric;

import com.eric.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-05-20 15:35
 * @since jdk-11.0.14
 */
public class AppForThirdBean
{
    public static void main(String[] args) throws SQLException
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println("dataSource = " + dataSource);
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
    }
}
