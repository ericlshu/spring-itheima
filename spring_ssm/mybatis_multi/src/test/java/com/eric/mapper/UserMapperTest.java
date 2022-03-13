package com.eric.mapper;

import com.eric.domain.User;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-13 16:50
 */
public class UserMapperTest {

    public static final Logger LOGGER = LogManager.getLogger();

    SqlSession sqlSession;
    UserMapper userMapper;

    @Before
    public void setUp() throws Exception
    {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void tearDown()
    {
        sqlSession.close();
    }

    @Test
    public void findAll()
    {
        PageHelper.startPage(1, 5);
        List<User> users = userMapper.findAll();
        for (User user : users)
        {
            LOGGER.info("users[" + user.getId() + "] : " + user);
        }
    }
}
