package com.eric.dao.impl;

import com.eric.dao.UserDao;
import com.eric.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-12 20:47
 */
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() throws IOException
    {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectList("userMapper.findAll");
    }
}
