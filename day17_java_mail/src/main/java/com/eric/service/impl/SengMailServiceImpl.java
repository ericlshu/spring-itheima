package com.eric.service.impl;

import com.eric.service.SendMailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description :
 *
 * @author Eric L SHU
 * @date 2022-03-31 12:51
 */
@Service
public class SengMailServiceImpl implements SendMailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail()
    {
        String from = "outlieric@vip.qq.com";
        String to = "shuli0502@hotmail.com";
        String subject = "[Java-Mail-Test]-邮件发送测试";
        String context = "Spring Boot Java Mail 整合邮件发送测试。";
        SimpleMailMessage mimeMessage = new SimpleMailMessage();
        mimeMessage.setFrom(from + "(Eric L SHU)");
        mimeMessage.setTo(to);
        mimeMessage.setSubject(subject);
        mimeMessage.setText(context);
        javaMailSender.send(mimeMessage);
    }
}
