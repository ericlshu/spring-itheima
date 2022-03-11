package com.eric.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 20:09
 */
public class AccountDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void out(String outMan, double money)
    {
        jdbcTemplate.update("update account set money=money-? where name=?", money, outMan);
    }

    public void in(String inMan, double money)
    {
        jdbcTemplate.update("update account set money=money+? where name=?", money, inMan);
    }
}
