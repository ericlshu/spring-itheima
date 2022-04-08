package com.eric.autoconfig;

import com.eric.service.IpCountService;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-04-08 21:10
 * @since jdk-11.0.14
 */
@EnableScheduling
public class IpAutoConfiguration
{
    @Bean
    public IpCountService ipCountService()
    {
        return new IpCountService();
    }
}
