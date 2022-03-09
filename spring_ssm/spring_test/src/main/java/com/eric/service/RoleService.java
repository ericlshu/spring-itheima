package com.eric.service;

import com.eric.dao.RoleDao;
import com.eric.domain.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-09 14:56
 */
public class RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao)
    {
        this.roleDao = roleDao;
    }

    public List<Role> list()
    {
        return roleDao.findAll();
    }

    public void save(Role role)
    {
        roleDao.save(role);
    }
}
