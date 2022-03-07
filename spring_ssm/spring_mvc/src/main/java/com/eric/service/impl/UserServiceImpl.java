package com.eric.service.impl;

import com.eric.dao.UserDao;
import com.eric.service.UserService;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 20:05
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    public void save()
    {
        System.out.println("saving user in service ...");
        userDao.save();
    }
}
