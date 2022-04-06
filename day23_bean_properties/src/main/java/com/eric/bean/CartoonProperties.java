package com.eric.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-06 21:48
 * @since jdk-11.0.14
 */
@Data
// @Component
@ConfigurationProperties(prefix = "cartoon")
public class CartoonProperties
{
    private Cat cat;
    private Mouse mouse;
}
