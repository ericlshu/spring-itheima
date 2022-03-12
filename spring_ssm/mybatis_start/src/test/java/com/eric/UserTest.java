package com.eric;

import com.eric.domain.User;
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
 * @date 2022-03-12 13:06
 */
public class UserTest {

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
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testFindAll()
    {
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        LOGGER.info("userList = " + userList);
    }


    @Test
    public void testFindById()
    {
        User user = sqlSession.selectOne("userMapper.findById", 1);
        LOGGER.info("user = " + user);
    }

    @Test
    public void testSave()
    {
        User user = new User("glen", "glen");
        int insert = sqlSession.insert("userMapper.save", user);
        LOGGER.info("insert result = " + insert);
    }

    @Test
    public void testUpdate()
    {
        User user = sqlSession.selectOne("userMapper.findById", 1);
        LOGGER.info("before user = " + user);

        user.setPassword("eric");
        int update = sqlSession.update("userMapper.update", user);
        LOGGER.info("update result = " + update);

        user = sqlSession.selectOne("userMapper.findById", 1);
        LOGGER.info("after user = " + user);
    }

    @Test
    public void testDelete()
    {
        int delete = sqlSession.delete("userMapper.deleteById", 1);
        LOGGER.info("delete result = " + delete);
    }
}
