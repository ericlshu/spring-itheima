package com.eric.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.eric.controller", "com.eric.config"})
@EnableWebMvc
public class SpringMVCConfig
{
}
