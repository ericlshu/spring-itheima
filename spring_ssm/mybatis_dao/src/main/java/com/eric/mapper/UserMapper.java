package com.eric.mapper;

import com.eric.domain.User;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-12 20:22
 */
public interface UserMapper {
    /**
     * find all user
     *
     * @return user list
     */
    List<User> findAll();

    /**
     * find user by id
     *
     * @param id id
     * @return user
     */
    User findById(int id);
}
