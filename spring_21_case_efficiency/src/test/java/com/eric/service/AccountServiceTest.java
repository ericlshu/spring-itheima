package com.eric.service;

import com.eric.config.SpringConfig;
import com.eric.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description :
 *
 * @author Eric SHU
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTest
{
    @Resource
    private AccountService accountService;

    @Test
    public void testFindById()
    {
        Account account = accountService.findById(2);
        // System.out.println("account = " + account);
    }

    @Test
    public void testFindAll()
    {
        List<Account> accounts = accountService.findAll();
        // accounts.forEach(System.out::println);
    }
}
