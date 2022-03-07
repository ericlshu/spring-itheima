package com.eric.dao.impl;

import com.eric.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 23:05
 */
// @Component("userDao")
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void save()
    {
        System.out.println("saving user in dao ...");
    }
}
