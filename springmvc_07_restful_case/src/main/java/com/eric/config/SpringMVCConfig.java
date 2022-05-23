package com.eric.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Description :
 *
 * @author Eric SHU
 */
@Configuration
@ComponentScan("com.eric.controller")
@EnableWebMvc
public class SpringMVCConfig
{
}
