import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 23:01
 */
public class DataSourceTest {
    @Test
    public void testC3P0() throws Exception
    {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/itheima");
        dataSource.setUser("eric");
        dataSource.setPassword("1234");
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
        connection.close();
    }


    @Test
    public void testDruid() throws Exception
    {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/itheima");
        dataSource.setUsername("eric");
        dataSource.setPassword("1234");
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
        connection.close();
    }

    @Test
    public void testC3P0ByProperties() throws Exception
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(resourceBundle.getString("jdbc.driver"));
        dataSource.setJdbcUrl(resourceBundle.getString("jdbc.url"));
        dataSource.setUser(resourceBundle.getString("jdbc.username"));
        dataSource.setPassword(resourceBundle.getString("jdbc.password"));
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);
        connection.close();
    }

    @Test
    public void testDataSourceBySpring() throws Exception
    {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        ComboPooledDataSource c3p0DataSource = applicationContext.getBean(ComboPooledDataSource.class);
        Connection connection = c3p0DataSource.getConnection();
        System.out.println("connection = " + connection);
        connection.close();

        DruidDataSource druidDataSource = (DruidDataSource) applicationContext.getBean("druidDataSource");
        connection = druidDataSource.getConnection();
        System.out.println("druidDataSource = " + druidDataSource);
        connection.close();
    }
}
