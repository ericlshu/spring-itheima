package com.eric.service.impl;

import com.eric.domain.SimCode;
import com.eric.service.SimCodeService;
import com.eric.utils.CodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-27 19:32
 */
@Slf4j
@Service("simCodeService")
public class SimCodeServiceImpl implements SimCodeService {

    @Resource(name = "codeUtils")
    private CodeUtils codeUtils;

    /**
     * 注解 @Cacheable 从cache空间中获取验证码
     * 注解 @CachePut 将生成的验证码放到cache空间中
     *
     * @param number phone number
     * @return validation code
     */
    @Override
    @CachePut(value = "simCode", key = "#number")
    public String sendCodeToSim(String number)
    {
        String code = codeUtils.generator(number);
        log.warn("code : " + code);
        return code;
    }

    @Override
    public boolean checkCode(SimCode simCode)
    {
        String paramCode = simCode.getCode();
        String cacheCode = codeUtils.get(simCode.getNumber());
        return paramCode.equals(cacheCode);
    }

}
