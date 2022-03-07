package com.eric;

import com.eric.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 23:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
// @ContextConfiguration(classes = {SpringConfiguration.class})
public class SpringJunitTest {

    @Autowired
    private UserService userService;
    @Resource(name = "c3p0DataSource")
    private DataSource dataSource;

    @Test
    public void test() throws SQLException
    {
        userService.save();
        System.out.println(dataSource.getConnection());
    }
}
