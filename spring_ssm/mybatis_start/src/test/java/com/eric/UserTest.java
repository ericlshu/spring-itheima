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

        // 会默认开启一个事务，但事务不会自动提交，也就意味着需要手动提交该事务，更新操作数据才会持久化到数据库中
        // sqlSession = sqlSessionFactory.openSession();

        // 参数为是否自动提交，如果设置为true，那么不需要手动提交事务
        sqlSession = sqlSessionFactory.openSession(true);
    }

    @After
    public void after()
    {
        // sqlSession.commit();
        sqlSession.close();
    }

    /**
     * <E> List<E> selectList(String statement, Object parameter)
     */
    @Test
    public void testFindAll()
    {
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        LOGGER.info("userList = " + userList);
        for (User user : userList)
        {
            LOGGER.warn("user = " + user);
        }
    }

    /**
     * <T> T selectOne(String statement, Object parameter)
     */
    @Test
    public void testFindById()
    {
        User user = sqlSession.selectOne("userMapper.findById", 1);
        LOGGER.info("user = " + user);
    }

    /**
     * int insert(String statement, Object parameter)s
     */
    @Test
    public void testSave()
    {
        User user = new User("glen", "glen");
        int insert = sqlSession.insert("userMapper.save", user);
        LOGGER.info("insert result = " + insert);
    }

    /**
     * int update(String statement, Object parameter)
     */
    @Test
    public void testUpdate()
    {
        User user = sqlSession.selectOne("userMapper.findById", 1);
        LOGGER.info("before user = " + user);

        user.setPassword("1234");
        int update = sqlSession.update("userMapper.update", user);
        LOGGER.info("update result = " + update);

        user = sqlSession.selectOne("userMapper.findById", 1);
        LOGGER.info("after user = " + user);
    }

    /**
     * int delete(String statement, Object parameter)
     */
    @Test
    public void testDelete()
    {
        int delete = sqlSession.delete("userMapper.deleteById", 1);
        LOGGER.info("delete result = " + delete);
    }
}
