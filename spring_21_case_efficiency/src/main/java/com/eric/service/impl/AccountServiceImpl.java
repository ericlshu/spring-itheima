package com.eric.service.impl;

import com.eric.dao.AccountDao;
import com.eric.domain.Account;
import com.eric.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService
{
    @Resource
    private AccountDao accountDao;

    public void save(Account account)
    {
        accountDao.save(account);
    }

    public void update(Account account)
    {
        accountDao.update(account);
    }

    public void delete(Integer id)
    {
        accountDao.delete(id);
    }

    public Account findById(Integer id)
    {
        return accountDao.findById(id);
    }

    public List<Account> findAll()
    {
        return accountDao.findAll();
    }
}
