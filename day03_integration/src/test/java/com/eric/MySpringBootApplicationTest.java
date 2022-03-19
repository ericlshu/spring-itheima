package com.eric;

import com.eric.dao.BookDao;
import com.eric.domain.User;
import com.eric.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads()
    {
        System.out.println("com.eric.MySpringBootApplicationTest.contextLoads ...");
        bookDao.save();
    }

    @Test
    void testGetUserById()
    {
        User user = userMapper.getById(1);
        System.out.println("user = " + user);
    }


    @Test
    void testFindAll()
    {
        List<User> userList = userMapper.findAll();
        for (User user : userList)
        {
            System.out.println("user = " + user);
        }
    }

}

