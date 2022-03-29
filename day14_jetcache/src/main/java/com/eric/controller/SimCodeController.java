package com.eric.controller;

import com.eric.domain.SimCode;
import com.eric.service.SimCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 19:33
 */
@RestController
@RequestMapping("/sim")
public class SimCodeController {

    @Resource(name = "simCodeService")
    private SimCodeService simCodeService;

    /**
     * send validation code to sim card
     *
     * @param number phone number
     * @return validation code
     */
    @GetMapping
    public String getCode(String number)
    {
        return simCodeService.sendCodeToSim(number);
    }

    /**
     * validate phone number with validation code
     *
     * @param simCode phone number and validation code
     * @return true : success, false : failed
     */
    @PostMapping
    public boolean checkCode(SimCode simCode)
    {
        return simCodeService.checkCode(simCode);
    }

}
