package com.eric.service;

import com.eric.dao.RoleDao;
import com.eric.dao.UserDao;
import com.eric.domain.Role;
import com.eric.domain.User;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-09 16:39
 */
public class UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    public void setUserDao(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao)
    {
        this.roleDao = roleDao;
    }

    public List<User> list()
    {
        List<User> userList = userDao.findAll();

        for (User user : userList)
        {
            Long id = user.getId();
            List<Role> roles = roleDao.findRolesByUserId(id);
            user.setRoles(roles);
        }

        return userList;
    }

    public void save(User user, Long[] roleIds)
    {
        Long userId = userDao.save(user);
        userDao.saveUserRoleMapping(userId, roleIds);


    }

    public void delete(Long userId)
    {
        userDao.deleteUserRoleMappingByUserId(userId);
        userDao.deleteUserById(userId);
    }
}
