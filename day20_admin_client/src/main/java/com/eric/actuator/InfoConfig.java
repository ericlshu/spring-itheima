package com.eric.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-04 11:56
 * @since jdk-11.0.14
 */
@Component
public class InfoConfig implements InfoContributor
{
    @Override
    public void contribute(Info.Builder builder)
    {
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("buildTime", "2022");
        builder.withDetail("runTime", System.currentTimeMillis())
                .withDetail("company", "Eric Inc.")
                .withDetails(infoMap);
    }
}
