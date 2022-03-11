package com.eric.service;

import com.eric.dao.AccountDao;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 20:10
 */
public class AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    public void transfer(String outMan, String inMan, double money)
    {
        accountDao.out(outMan, money);
        int i = 1 / 0;
        accountDao.in(inMan, money);
    }
}
