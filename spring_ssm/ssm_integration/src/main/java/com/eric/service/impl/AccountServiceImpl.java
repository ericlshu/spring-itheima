package com.eric.service.impl;

import com.eric.domain.Account;
import com.eric.mapper.AccountMapper;
import com.eric.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-14 13:39
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Resource(name = "accountMapper")
    AccountMapper accountMapper;

    @Override
    public void save(Account account)
    {
        /*try
        {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            accountMapper.save(account);
            sqlSession.commit();
            sqlSession.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/

        accountMapper.save(account);
    }

    @Override
    public List<Account> findAll()
    {
        /*try
        {
            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
            List<Account> accountList = accountMapper.findAll();
            sqlSession.close();
            return accountList;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }*/

        return accountMapper.findAll();
    }
}
