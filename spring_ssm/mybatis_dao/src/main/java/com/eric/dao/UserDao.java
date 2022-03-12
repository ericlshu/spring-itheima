package com.eric.dao;

import com.eric.domain.User;

import java.io.IOException;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-12 20:47
 */
public interface UserDao {
    /**
     * find all user
     * @return user list
     * @throws IOException IOException
     */
    List<User> findAll() throws IOException;
}
