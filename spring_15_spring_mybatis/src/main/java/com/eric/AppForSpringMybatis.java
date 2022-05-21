package com.eric;

import com.eric.config.SpringConfig;
import com.eric.domain.Account;
import com.eric.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Description : 使用Spring整合Mybatis
 * <p>
 * 步骤1:项目中导入整合需要的jar包
 * -->> spring-jdbc
 * -->> mybatis-spring
 * 步骤2:创建Spring的主配置类
 * -->> SpringConfig
 * 步骤3:创建数据源的配置类
 * -->> JdbcConfig
 * 步骤4:主配置类中读properties并引入数据源配置类
 * -->> @PropertySource("classpath:jdbc.properties")
 * -->> @Import({JdbcConfig.class})
 * 步骤5:创建Mybatis配置类并配置SqlSessionFactory
 * -->> MybatisConfig
 * 步骤6:主配置类中引入Mybatis配置类
 * -->> @Import({JdbcConfig.class, MybatisConfig.class})
 *
 * @author Eric L SHU
 * @date 2022-05-21 11:15
 * @since jdk-11.0.14
 */
public class AppForSpringMybatis
{
    public static void main(String[] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = context.getBean(AccountService.class);
        Account account = accountService.findById(1);
        System.out.println("account = " + account);
        List<Account> accounts = accountService.findAll();
        accounts.forEach(System.out::println);
    }
}
