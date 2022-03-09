package com.eric.dao;

import com.eric.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-09 16:40
 */
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll()
    {
        return jdbcTemplate.query("select * from sys_user", new BeanPropertyRowMapper<>(User.class));
    }
}
