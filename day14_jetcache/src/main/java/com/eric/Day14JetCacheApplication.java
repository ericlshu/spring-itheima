package com.eric;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shuli
 */
@SpringBootApplication
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = {"com.eric"})
public class Day14JetCacheApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(Day14JetCacheApplication.class, args);
    }

}
