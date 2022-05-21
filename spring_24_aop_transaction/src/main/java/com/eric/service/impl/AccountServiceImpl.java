package com.eric.service.impl;

import com.eric.dao.AccountDao;
import com.eric.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService
{
    @Resource
    private AccountDao accountDao;

    public void transfer(String out, String in, Double money)
    {
        accountDao.outMoney(out, money);
        int i = 1 / 0;
        accountDao.inMoney(in, money);
    }
}
