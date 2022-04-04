package com.eric.actuator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-04 12:05
 * @since jdk-11.0.14
 */
@Component
public class HealthConfig extends AbstractHealthIndicator
{
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception
    {
        boolean condition = false;
        if (condition)
        {
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("buildTime", "2022");
            builder.withDetail("runTime", System.currentTimeMillis())
                    .withDetail("company", "Eric Inc.")
                    .withDetails(infoMap)
                    .status(Status.UP);
        }
        else
        {
            // builder.status(Status.DOWN);
            builder.status(Status.OUT_OF_SERVICE);
        }
    }
}
