package com.eric.service;

import com.eric.dao.UserDao;
import com.eric.dao.impl.UserDaoImpl;
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
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-12 20:31
 */
public class ServiceDemo {

    public static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testTraditionalDao() throws IOException
    {
        UserDao userDao = new UserDaoImpl();
        List<User> userList = userDao.findAll();
        for (User user : userList)
        {
            LOGGER.info("user = " + user);
        }
    }

    SqlSession sqlSession;

    @Before
    public void before() throws IOException
    {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sessionFactory.openSession();
    }

    @After
    public void after()
    {
        sqlSession.close();
    }

    @Test
    public void testProxyDao()
    {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        for (User user : userList)
        {
            LOGGER.info("user = " + user);
        }

        User user = userMapper.findById(1);
        LOGGER.warn("user = " + user);
    }
}
