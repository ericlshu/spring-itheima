package com.eric;

import com.eric.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Test
    public void testFindAll() throws IOException
    {        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> userList = sqlSession.selectList("userMapper.findAll");

        LOGGER.debug("userList = " + userList);

        sqlSession.close();

    }
}
