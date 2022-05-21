package com.eric.service;

import com.eric.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Description : AOP事务管理及配置
 * <p>
 * AOP事务管理
 * 步骤1:在需要被事务管理的方法上添加注解
 * -->> AccountService -> @Transactional
 * 步骤2:在JdbcConfig类中配置事务管理器
 * -->> JdbcConfig -> transactionManager
 * 步骤3:开启注解式事务驱动
 * -->> JdbcConfig -> @EnableTransactionManagement
 * <p>
 * AOP事务配置
 * -->> readOnly：true只读事务，false读写事务，增删改要设为false,查询设为true。
 * -->> timeout:设置超时时间单位秒，在多长时间之内事务没有提交成功就自动回滚，-1表示不设置超时时间。
 * -->> rollbackFor:当出现指定异常进行事务回滚
 * -->> noRollbackFor:当出现指定异常不进行事务回滚
 * --->>> Spring的事务只会对`Error异常`和`RuntimeException异常`及其子类进行事务回顾，其他的异常类型是不会回滚
 * -->> rollbackForClassName等同于rollbackFor,只不过属性为异常的类全名字符串
 * -->> noRollbackForClassName等同于noRollbackFor，只不过属性为异常的类全名字符串
 * -->> isolation设置事务的隔离级别
 * --->>> DEFAULT          : 默认隔离级别, 会采用数据库的隔离级别
 * --->>> READ_UNCOMMITTED : 读未提交
 * --->>> READ_COMMITTED   : 读已提交
 * --->>> REPEATABLE_READ  : 重复读取
 * --->>> SERIALIZABLE     : 串行化
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
    public void testTransfer() throws IOException
    {
        accountService.transfer("Tom", "Jerry", 100D);
    }
}
