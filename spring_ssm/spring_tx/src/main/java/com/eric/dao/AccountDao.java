package com.eric.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-11 20:09
 */
@Repository("accountDao")
public class AccountDao {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void out(String outMan, double money)
    {
        jdbcTemplate.update("update account set money=money-? where name=?", money, outMan);
    }

    public void in(String inMan, double money)
    {
        jdbcTemplate.update("update account set money=money+? where name=?", money, inMan);
    }
}
