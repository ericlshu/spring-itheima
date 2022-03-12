package com.eric.service;

import com.eric.domain.User;
import com.eric.mapper.UserMapper;
import com.eric.mapper.impl.UserMapperImpl;
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
    public void test() throws IOException
    {

        UserMapper userMapper = new UserMapperImpl();
        List<User> userList = userMapper.findAll();
        for (User user : userList)
        {
            LOGGER.info("user = " + user);
        }

    }
}
