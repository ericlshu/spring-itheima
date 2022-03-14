package com.eric.mapper;

import com.eric.domain.Account;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-14 13:36
 */
public interface AccountMapper {

    /**
     * save account
     *
     * @param account account
     */
    void save(Account account);

    /**
     * find all account(s)
     *
     * @return account list
     */
    List<Account> findAll();
}
