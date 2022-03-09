package com.eric.dao;

import com.eric.domain.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-09 14:58
 */
public class RoleDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Role> findAll()
    {
        return jdbcTemplate.query("select * from sys_role", new BeanPropertyRowMapper<>(Role.class));
    }

    public void save(Role role)
    {
        jdbcTemplate.update("insert into sys_role values(?,?,?)", null, role.getRoleName(), role.getRoleDesc());
    }

    public List<Role> findRolesByUserId(Long id)
    {
        return jdbcTemplate.query(
                "select r.* from sys_user_role ur join sys_role r on r.id = ur.roleId where ur.userId = ?",
                new BeanPropertyRowMapper<>(Role.class), id);
    }
}
