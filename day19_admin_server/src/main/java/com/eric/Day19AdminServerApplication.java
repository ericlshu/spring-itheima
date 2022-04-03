package com.eric;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class Day19AdminServerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(Day19AdminServerApplication.class, args);
    }
}
