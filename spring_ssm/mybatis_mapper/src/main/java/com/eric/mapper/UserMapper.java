package com.eric.mapper;

import com.eric.domain.User;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-12 21:18
 */
public interface UserMapper {
    /**
     * find user by condition
     *
     * @param user user condition like username or id
     * @return user object
     */
    List<User> findByCondition(User user);

    /**
     * find user(s) by multiple ids
     *
     * @param ids id list
     * @return user list
     */
    List<User> findByIds(List<Integer> ids);
}
