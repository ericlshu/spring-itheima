package com.eric;

import com.eric.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 22:44
 */
public class UserDaoTest {
    @Test
    public void saveTest()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.save();
    }
}
