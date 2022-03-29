package com.eric.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-29 21:16
 */
@Configuration
public class XMemcachedConfig {

    @Autowired
    private XMemcachedProperties properties;

    @Bean
    public MemcachedClient getMemcachedClient() throws IOException
    {
        return new XMemcachedClientBuilder("localhost:11211").build();
    }
}
