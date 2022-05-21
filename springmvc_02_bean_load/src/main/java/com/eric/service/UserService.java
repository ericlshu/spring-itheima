package com.eric.service;

import com.eric.domain.User;

public interface UserService
{
    void save(User user);

    User findById(Integer id);
}
