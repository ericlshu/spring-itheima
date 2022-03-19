package com.eric;

import com.eric.dao.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-19 19:52
 */
@SpringBootTest
public class MySpringBootApplicationTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads()
    {
        System.out.println("com.eric.MySpringBootApplicationTest.contextLoads ...");
        bookDao.save();
    }

}

