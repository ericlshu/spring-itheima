package com.eric;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day07AdvanceConfigApplicationTests {

    // 宽松绑定不支持注解@Value引用单个属性的方式
    @Value("${servers.ipAddress}")
    private String ipAddress;

    @Test
    void contextLoads()
    {
        System.out.println("ipAddress = " + ipAddress);
    }

}
