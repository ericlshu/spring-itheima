package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Eric SHU
 */
@SpringBootApplication
public class Day06HotDeployApplication {

    public static void main(String[] args)
    {
        // 设置高优先级属性禁用热部署
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Day06HotDeployApplication.class, args);
    }

}
