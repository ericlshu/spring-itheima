package com.eric;

import com.eric.config.SpringConfiguration;
import com.eric.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 23:07
 */
public class AnnotationTest {
    @Test
    public void testXml()
    {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        userService.save();
        applicationContext.close();
    }


    @Test
    public void testAnno()
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.save();
    }
}
