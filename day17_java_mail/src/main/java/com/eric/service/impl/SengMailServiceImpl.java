package com.eric.service.impl;

import com.eric.service.SendMailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

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

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String HTML_NEW_LINE = "<br>";

    @Override
    public void sendMail()
    {
        String from = "outlieric@vip.qq.com";
        String to = "shuli0502@hotmail.com";
        String subject = "[Java-Mail-Test]-邮件发送测试";
        String context = "Spring Boot Java Mail 整合邮件发送测试。" + HTML_NEW_LINE
                + "<a href='https://gitee.com/ericlshu/springboot'>点击链接访问源码</a>" + HTML_NEW_LINE
                + "<img src='https://images.unsplash.com/photo-1647774973248-0e25559f3b6d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80'/>" + HTML_NEW_LINE;

        // SimpleMailMessage message = new SimpleMailMessage();
        // message.setFrom(from + "(Eric L SHU)");
        // message.setTo(to);
        // message.setSubject(subject);
        // message.setText(context);

        try
        {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            messageHelper.setFrom(from + "(Eric L SHU)");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(context, true);

            File bookImage = new File("D:\\Sandbox\\Workspace\\IntelliJIdea\\springboot\\day17_java_mail\\src\\main\\resources\\books.jpg");
            File jarPackage = new File("D:\\Sandbox\\Workspace\\IntelliJIdea\\springboot\\day17_java_mail\\target\\day17_java_mail-0.0.1-SNAPSHOT.jar");
            messageHelper.addAttachment(bookImage.getName(), bookImage);
            messageHelper.addAttachment("SpringBoot-JavaMail.jar", jarPackage);

            javaMailSender.send(mimeMessage);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }
}
