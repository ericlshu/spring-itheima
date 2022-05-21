package com.eric.service.impl;

import com.eric.dao.UserDao;
import com.eric.domain.User;
import com.eric.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService
{
    @Resource
    private UserDao userDao;

    public void save(User user)
    {
        System.out.println("user service ...");
    }

    @Override
    public User findById(Integer id)
    {
        return userDao.findById(id);
    }
}
