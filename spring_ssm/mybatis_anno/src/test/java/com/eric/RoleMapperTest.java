package com.eric;

import com.eric.domain.Role;
import com.eric.mapper.RoleMapper;
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
 * @date 2022-03-14 12:57
 */
public class RoleMapperTest {

    public static final Logger LOGGER = LogManager.getLogger();

    SqlSession sqlSession;
    RoleMapper roleMapper;

    @Before
    public void setUp() throws Exception
    {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession(true);
        roleMapper = sqlSession.getMapper(RoleMapper.class);
    }

    @After
    public void tearDown()
    {
        sqlSession.close();
    }

    @Test
    public void findRolesByUserId()
    {
        List<Role> roleList = roleMapper.findRolesByUserId(1);
        for (Role role : roleList)
        {
            LOGGER.info(role);
        }
    }
}
