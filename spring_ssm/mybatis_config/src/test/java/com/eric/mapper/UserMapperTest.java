package com.eric.mapper;

import com.eric.domain.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @date 2022-03-13 09:49
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
    public void save()
    {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setBirthday(new Date());
        userMapper.save(user);
    }

    @Test
    public void findById()
    {
        User user = userMapper.findById(17);
        LOGGER.info("user : " + user);
    }

    @Test
    public void findALl()
    {
        PageHelper.startPage(11, 10);

        List<User> userList = userMapper.findAll();
        for (User u : userList)
        {
            LOGGER.info("users[" + u.getId() + "] : " + u);
        }

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        LOGGER.warn("Total records : " + pageInfo.getTotal());
        LOGGER.warn("Total pages   : " + pageInfo.getPages());
        LOGGER.warn("Current page  : " + pageInfo.getPageNum());
        LOGGER.warn("Previous page : " + pageInfo.getPrePage());
        LOGGER.warn("Next page     : " + pageInfo.getNextPage());
        LOGGER.warn("Page size     : " + pageInfo.getPageSize());
        LOGGER.warn("Is first page : " + pageInfo.isIsFirstPage());
        LOGGER.warn("Is last page  : " + pageInfo.isIsLastPage());
    }
}
