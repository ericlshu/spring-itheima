package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启用缓存 @EnableCaching
 *
 * @author shuli
 */
@SpringBootApplication
@EnableCaching
public class Day13CacheApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(Day13CacheApplication.class, args);
    }

}
