package com;

import com.eric.MySpringBootApplication;
import com.eric.dao.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Description :
 *
 * @author Eric L SHU
 * @ SpringBootTest
 * @ ContextConfiguration(classes = MySpringBootApplication.class)
 * @date 2022-03-19 19:52
 */
@SpringBootTest(classes = MySpringBootApplication.class)
public class MySpringBootApplicationTest1 {

    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads()
    {
        System.out.println("com.eric.MySpringBootApplicationTest1.contextLoads ...");
        bookDao.save();
    }

}

