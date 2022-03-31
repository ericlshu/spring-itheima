package com.eric;

import com.eric.service.SendMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Day17JavaMailApplicationTests {

    @Autowired
    private SendMailService sendMailService;

    @Test
    void contextLoads()
    {
        sendMailService.sendMail();
    }

}
