package com.eric;

import com.eric.dao.UserDao;
import com.eric.factory.DynamicFactory;
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
        ApplicationContext applicationContext;
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // applicationContext = new FileSystemXmlApplicationContext("D:\\Sandbox\\Workspace\\IntelliJIdea\\itheima_ssh\\ssm\\ssh_ioc\\src\\main\\resources\\applicationContext.xml");

        UserDao userDao;
        userDao = (UserDao) applicationContext.getBean("userDao1");
        userDao = (UserDao) applicationContext.getBean("userDao2");
        userDao = (UserDao) applicationContext.getBean("userDao3");
        userDao = (UserDao) applicationContext.getBean("userDao");

        DynamicFactory factory = applicationContext.getBean(DynamicFactory.class);
        userDao = factory.getUserDao();

        userDao.save();
    }
}
