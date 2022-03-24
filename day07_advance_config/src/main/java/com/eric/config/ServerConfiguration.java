package com.eric.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description :
 *
 * @author Eric L SHU
 * @ EnableConfigurationProperties与@ Component不能同时使用
 * @date 2022-03-24 11:53
 */
// @Component
@Data
@ConfigurationProperties(prefix = "servers")
public class ServerConfiguration {

    private String ipAddress;
    private int port;
    private long timeout;

}

