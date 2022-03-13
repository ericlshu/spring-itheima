package com.eric.mapper;

import com.eric.domain.User;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-13 09:33
 */
public interface UserMapper {
    /**
     * save user
     *
     * @param user user
     */
    void save(User user);

    /**
     * find user by id
     *
     * @param id id
     * @return user
     */
    User findById(int id);

    /**
     * find all user
     *
     * @return user list
     */
    List<User> findAll();
}
