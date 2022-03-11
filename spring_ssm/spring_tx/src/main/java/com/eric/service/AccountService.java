package com.eric.service;

import com.eric.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 20:10
 */
@Service("accountService")
@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = {})
public class AccountService {

    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Transactional(
            isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            timeout = 10,
            readOnly = false)
    public void transfer(String outMan, String inMan, double money)
    {
        accountDao.out(outMan, money);
        // int i = 1 / 0;
        accountDao.in(inMan, money);
    }
}
