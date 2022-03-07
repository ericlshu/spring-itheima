package com.eric.service.impl;

import com.eric.dao.UserDao;
import com.eric.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 20:05
 */
// @Component("userService")
@Service("userService")
public class UserServiceImpl implements UserService {

    // @Autowired              // 按照数据类型进行注入（可以单独使用）
    // @Qualifier("userDao")   // 按照实例名称进行注入（需配合Autowired使用）
    @Resource(name="userDao")  // @Resource相当于@Autowired+@Qualifier
    private UserDao userDao;

    // Set 方法在使用注解时可以省略不写
    // public void setUserDao(UserDao userDao)
    // {
    //     this.userDao = userDao;
    // }

    @Value("eric")
    private String username;

    @Value("${jdbc.driver}")
    private String driver;

    @Override
    public void save()
    {
        System.out.println("saving user in service ...");
        System.out.println("username : " + this.username);
        System.out.println("driver   : " + this.driver);
        userDao.save();
    }

    @PostConstruct
    public void init(){
        System.out.println("init service object ...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy service object ...");
    }
}
