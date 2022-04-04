package com.eric.actuator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-04 12:23
 * @since jdk-11.0.14
 */
@Slf4j
@Component
@Endpoint(id = "pay", enableByDefault = true)
public class PayEndpoint
{
    @ReadOperation
    public Object getPay()
    {
        //调用业务操作，获取支付相关信息结果，最终return出去
        Map<String, Integer> payMap = new HashMap<>(10);
        payMap.put("level 1", 103);
        payMap.put("level 2", 315);
        payMap.put("level 3", 666);
        log.warn("payMap = " + payMap);
        return payMap;
    }
}
