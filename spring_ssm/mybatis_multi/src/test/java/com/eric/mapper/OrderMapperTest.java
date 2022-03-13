package com.eric.mapper;

import com.eric.domain.Order;
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

import static org.junit.Assert.*;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-13 15:57
 */
public class OrderMapperTest {

    public static final Logger LOGGER = LogManager.getLogger();

    SqlSession sqlSession;
    OrderMapper orderMapper;

    @Before
    public void setUp() throws Exception
    {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession(true);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @After
    public void tearDown()
    {
        sqlSession.close();
    }

    @Test
    public void findAll()
    {
        PageHelper.startPage(1,10);
        List<Order> orderList = orderMapper.findAll();
        for (Order order : orderList)
        {
            LOGGER.info("users[" + order.getId() + "] : " + order);
        }
    }
}
