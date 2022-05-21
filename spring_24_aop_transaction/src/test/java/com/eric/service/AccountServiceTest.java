package com.eric.service;

import com.eric.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description : AOP事务管理
 * <p>
 * 步骤1:在需要被事务管理的方法上添加注解
 * -->> AccountService -> @Transactional
 * 步骤2:在JdbcConfig类中配置事务管理器
 * -->> JdbcConfig -> transactionManager
 * 步骤3:开启注解式事务驱动
 * -->> JdbcConfig -> @EnableTransactionManagement
 *
 * @author Eric SHU
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTest
{
    @Autowired
    private AccountService accountService;

    @Test
    public void testTransfer()
    {
        accountService.transfer("Tom", "Jerry", 100D);
    }
}
