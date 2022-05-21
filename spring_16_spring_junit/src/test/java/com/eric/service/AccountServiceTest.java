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
 * Description : Spring整合Junit
 * <p>
 * ---> 单元测试，如果测试的是注解配置类，则使用`@ContextConfiguration(classes = 配置类.class)`
 * ---> 单元测试，如果测试的是配置文件，则使用`@ContextConfiguration(locations={配置文件名,...})`
 * ---> Junit运行后是基于Spring环境运行的，所以Spring提供了一个专用的类运行器，这个务必要设置，这个类运行器就在Spring的测试专用包中提供的，导入的坐标就是这个东西`SpringJUnit4ClassRunner`
 * ---> 上面两个配置都是固定格式，当需要测试哪个bean时，使用自动装配加载对应的对象，下面的工作就和以前做Junit单元测试完全一样了
 *
 * @author Eric L SHU
 * @date 2022-05-21 11:57
 * @since jdk-11.0.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTest
{
    @Resource
    AccountService accountService;

    @Test
    public void test()
    {
        List<Account> accounts = accountService.findAll();
        accounts.forEach(System.out::println);
    }
}
