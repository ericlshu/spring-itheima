package com.eric;

import com.eric.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 20:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AccountTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testTransfer()
    {
        accountService.transfer("tom", "eric", 500);
    }

}
