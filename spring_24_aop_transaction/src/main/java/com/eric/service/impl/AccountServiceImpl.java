package com.eric.service.impl;

import com.eric.dao.AccountDao;
import com.eric.service.AccountService;
import com.eric.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService
{
    @Resource
    private AccountDao accountDao;

    @Resource
    private LogService logService;

    public void transfer(String out, String in, Double money)
    {
        try
        {
            accountDao.outMoney(out, money);
            // int i = 1 / 0;
            // if(true) throw new IOException();
            accountDao.inMoney(in, money);
        }
        finally
        {
            logService.log(out, in, money);
        }
    }
}
