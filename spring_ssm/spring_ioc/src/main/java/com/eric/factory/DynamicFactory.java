package com.eric.factory;

import com.eric.dao.UserDao;
import com.eric.dao.impl.UserDaoImpl;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 22:49
 */
public class DynamicFactory {
    public UserDao getUserDao()
    {
        return new UserDaoImpl();
    }
}
