package com.eric.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Description :
 *
 * @author Eric L SHU
 * 注： @EnableConfigurationProperties 与 @Component不能同时使用
 * 3. @Validated开启对当前bean的属性注入校验
 * @date 2022-03-24 11:53
 */
// @Component
@Data
@ConfigurationProperties(prefix = "servers")
@Validated
public class ServerConfiguration {

    private String ipAddress;

    /**
     * 4. 设置校验规则
     */
    @Max(value = 8888, message = "最大值不能超过8888")
    @Min(value = 2222, message = "最小值不能低于2222")
    private int port;
    private long timeout;

    @DurationUnit(ChronoUnit.HOURS)
    private Duration serverTimeOut;

    @DataSizeUnit(DataUnit.MEGABYTES)
    private DataSize dataSize;
}

