package com.eric.service;

import com.eric.domain.Account;

import java.util.List;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-14 13:38
 */
public interface AccountService {

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
