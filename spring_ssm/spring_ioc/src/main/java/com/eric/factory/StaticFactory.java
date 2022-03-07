package com.eric.factory;

import com.eric.dao.UserDao;
import com.eric.dao.impl.UserDaoImpl;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-07 22:50
 */
public class StaticFactory {
    public static UserDao getUserDao()
    {
        return new UserDaoImpl();
    }
}
