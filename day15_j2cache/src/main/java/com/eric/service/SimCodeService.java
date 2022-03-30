package com.eric.service;

import com.eric.domain.SimCode;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 19:30
 */
public interface SimCodeService {
    /**
     * send validation code to sim card
     *
     * @param number phone number
     * @return validation code
     */
    String sendCodeToSim(String number);

    /**
     * validate phone number with validation code
     *
     * @param simCode code to sim card
     * @return true : success, false : failed
     */
    boolean checkCode(SimCode simCode);
}
