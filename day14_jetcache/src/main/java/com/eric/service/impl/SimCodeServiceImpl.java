package com.eric.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.eric.domain.SimCode;
import com.eric.service.SimCodeService;
import com.eric.utils.CodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    @CreateCache(area = "default", name = "jetCache", expire = 10, timeUnit = TimeUnit.SECONDS,cacheType = CacheType.LOCAL)
    private Cache<String, String> jetCache;

    @Override
    public String sendCodeToSim(String number)
    {
        String code = codeUtils.generator(number);
        jetCache.put(number, code);
        return code;
    }

    @Override
    public boolean checkCode(SimCode simCode)
    {
        log.warn(simCode.toString());
        String cacheCode = jetCache.get(simCode.getNumber());
        String paramCode = simCode.getCode();
        return paramCode.equals(cacheCode);
    }

}
