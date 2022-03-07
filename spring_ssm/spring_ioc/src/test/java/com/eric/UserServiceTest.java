package com.eric;

import com.eric.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 22:53
 */
public class UserServiceTest {
    @Test
    public void saveTest()
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService;
        userService = (UserService) applicationContext.getBean("userService1");
        userService = (UserService) applicationContext.getBean("userService2");
        userService = (UserService) applicationContext.getBean("userService3");
        userService.save();
    }
}
