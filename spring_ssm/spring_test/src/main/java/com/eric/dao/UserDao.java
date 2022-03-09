package com.eric.dao;

import com.eric.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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

    public Long save(User user)
    {
        // userId为空 无法正常插入
        /*jdbcTemplate.update("insert into sys_user values(?,?,?,?,?)",
                null, user.getUsername(), user.getEmail(), user.getPassword(), user.getPhoneNum());*/

        PreparedStatementCreator statementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException
            {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "insert into sys_user values(?,?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1,null);
                preparedStatement.setString(2,user.getUsername());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());
                preparedStatement.setString(5,user.getPhoneNum());
                return preparedStatement;
            }
        };

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(statementCreator, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void saveUserRoleMapping(Long userId, Long[] roleIds)
    {
        for (Long roleId : roleIds)
        {
            jdbcTemplate.update("insert into sys_user_role values(?,?)", userId, roleId);
        }

    }
}
