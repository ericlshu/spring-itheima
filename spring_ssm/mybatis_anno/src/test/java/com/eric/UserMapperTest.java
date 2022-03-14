package com.eric;

import com.eric.domain.Order;
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

import java.io.InputStream;
import java.util.Date;
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
    public void testSave()
    {
        User user = new User();
        user.setUsername("tom");
        user.setPassword("abc");
        user.setBirthday(new Date());
        userMapper.save(user);
    }

    @Test
    public void testUpdate()
    {
        User user = new User();
        user.setId(1000);
        user.setUsername("lucy");
        user.setPassword("123");
        userMapper.update(user);
    }

    @Test
    public void testDelete()
    {
        userMapper.delete(1000);
    }

    @Test
    public void testFindById()
    {
        User user = userMapper.findById(2);
        LOGGER.info(user);
    }

    @Test
    public void testFindAll()
    {
        List<User> all = userMapper.findAll();
        for (User user : all)
        {
            LOGGER.info(user);
        }
    }

    @Test
    public void testFindUserAndOrders()
    {
        List<User> all = userMapper.findUserAndOrders();
        for (User user : all)
        {
            LOGGER.info(user);
            List<Order> orderList = user.getOrderList();
            for (Order order : orderList)
            {
                LOGGER.warn(order);
            }
            LOGGER.info("----------------------------------------------------------------------------------");
        }
    }
}
