package com.eric.test;

import com.eric.domain.User;
import com.eric.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-12 21:23
 */
public class MapperTest {

    public static final Logger LOGGER = LogManager.getLogger();

    SqlSession sqlSession;

    @Before
    public void before() throws IOException
    {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void after()
    {
        sqlSession.close();
    }

    @Test
    public void test()
    {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User condition = new User();
        // condition.setId(1);
        condition.setUsername("eric");
        condition.setPassword("1234");

        List<User> userList = userMapper.findByCondition(condition);
        for (User u : userList)
        {
            LOGGER.info("users[" + u.getId() + "] : " + u);
        }

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        // ids.add(3);
        ids.add(5);

        userList = userMapper.findByIds(ids);
        for (User u : userList)
        {
            LOGGER.warn("users[" + u.getId() + "] : " + u);
        }
    }
}
