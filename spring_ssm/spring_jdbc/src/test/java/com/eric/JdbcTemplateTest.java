package com.eric;

import com.eric.domain.Account;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-09 11:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateTest {

    ComboPooledDataSource dataSource;
    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    // @Before
    public void init() throws PropertyVetoException
    {
        System.out.println("init ...");

        dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/itheima");
        dataSource.setUser("eric");
        dataSource.setPassword("1234");

        // Connection connection = dataSource.getConnection();
        // System.out.println("connection = " + connection);

        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }

    @Test
    public void testInsert()
    {
        int row = jdbcTemplate.update("insert into account values(?,?)", "jerry", 5000);
        System.out.println("row = " + row);
    }

    @Test
    public void testQueryAll()
    {
        List<Account> accounts = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<>(Account.class));
        System.out.println("accounts = " + accounts);
    }

    @Test
    public void testQueryOne()
    {
        Account account = jdbcTemplate.queryForObject("select * from account where name = ?", new BeanPropertyRowMapper<>(Account.class), "tom");
        System.out.println("account = " + account);
    }

    @Test
    public void testQueryCount()
    {
        Long count = jdbcTemplate.queryForObject("select count(1) from account", Long.class);
        System.out.println("count = " + count);
    }
}
