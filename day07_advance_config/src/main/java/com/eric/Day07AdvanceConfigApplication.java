package com.eric;

import com.alibaba.druid.pool.DruidDataSource;
import com.eric.config.ServerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Eric SHU
 */
@SpringBootApplication
@EnableConfigurationProperties({ServerConfiguration.class})
public class Day07AdvanceConfigApplication {

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public DruidDataSource dataSource()
    {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    public static void main(String[] args)
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Day07AdvanceConfigApplication.class, args);
        ServerConfiguration serverConfiguration = applicationContext.getBean(ServerConfiguration.class);
        System.out.println("serverConfiguration = " + serverConfiguration);

        DruidDataSource druidDataSource = applicationContext.getBean(DruidDataSource.class);
        System.out.println("druidDataSource = " + druidDataSource);
        System.out.println("DriverClassName = " + druidDataSource.getDriverClassName());
    }

}
