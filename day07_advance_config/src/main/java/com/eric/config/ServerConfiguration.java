package com.eric.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-24 11:53
 */
@Component
@Data
@ConfigurationProperties(prefix = "servers")
public class ServerConfiguration {

    private String ipAddress;
    private int port;
    private long timeout;

}

