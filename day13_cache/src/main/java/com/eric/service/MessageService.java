package com.eric.service;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 18:44
 */
public interface MessageService {
    /**
     * get message by phone number
     *
     * @param number phone number
     * @return validation code
     */
    String getCode(String number);

    /**
     * validate phone number and validation code
     *
     * @param number phone number
     * @param code   validation code
     * @return true : success, false : failed
     */
    boolean check(String number, String code);
}
