package com.eric;

import com.eric.domain.Order;
import com.eric.mapper.OrderMapper;
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
        List<Order> orderList = orderMapper.findAllOrderWithUser2();
        for (Order order : orderList)
        {
            LOGGER.info("orders[" + order.getId() + "] : " + order);
            LOGGER.warn("user : " + order.getUser());
            LOGGER.warn("----------------------------------------------------------------------------------");
        }
    }


    @Test
    public void findOrdersByUid()
    {
        // PageHelper.startPage(1,10);
        List<Order> orderList = orderMapper.findOrdersByUserId(2);
        for (Order order : orderList)
        {
            LOGGER.info("orders[" + order.getId() + "] : " + order);
            LOGGER.warn("----------------------------------------------------------------------------------");
        }
    }
}
