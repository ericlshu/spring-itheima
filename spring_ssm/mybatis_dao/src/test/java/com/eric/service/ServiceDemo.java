package com.eric.service;

import com.eric.dao.UserDao;
import com.eric.dao.impl.UserDaoImpl;
import com.eric.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-12 20:31
 */
public class ServiceDemo {

    public static final Logger LOGGER = LogManager.getLogger();

    @Test
    public void testTraditionalDao() throws IOException
    {

        UserDao userDao = new UserDaoImpl();
        List<User> userList = userDao.findAll();
        for (User user : userList)
        {
            LOGGER.info("user = " + user);
        }

    }
}
