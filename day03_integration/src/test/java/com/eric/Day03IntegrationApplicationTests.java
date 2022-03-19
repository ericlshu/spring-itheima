package com.eric;

import com.eric.dao.BookDao;
import com.eric.domain.User;
import com.eric.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Day03IntegrationApplicationTests {

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

    @Test
    void testSelectById()
    {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList)
        {
            System.out.println("user = " + user);
        }
    }

}
