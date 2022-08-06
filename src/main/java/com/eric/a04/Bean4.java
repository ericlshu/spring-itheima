package com.eric.a04;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * java.home=
 * java.version=
 */
@Data
@ConfigurationProperties(prefix = "java")
public class Bean4
{
    private String home;
    private String version;
}
