package com.eric.service.impl;

import com.eric.domain.SimCode;
import com.eric.service.SimCodeService;
import com.eric.utils.CodeUtils;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CacheChannel cacheChannel;

    @Override
    public String sendCodeToSim(String number)
    {
        String code = codeUtils.generator(number);
        cacheChannel.set("sim", number, code);
        return code;
    }

    @Override
    public boolean checkCode(SimCode simCode)
    {
        log.warn(simCode.toString());
        String cacheCode = cacheChannel.get("sim", simCode.getNumber()).asString();
        String paramCode = simCode.getCode();
        return paramCode.equals(cacheCode);
    }

}
