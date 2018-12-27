package com.alan.springboothelloworld.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spirngboot方式发送邮箱  第三种方式，支持springboot，最简洁
 */
@RestController
public class MailController {

    @Autowired
    private JavaMailSender jms;

    @RequestMapping("mail/test")
    public String test(){
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom("发送者邮箱"); //  配置中的邮箱
        //接收者
        mainMessage.setTo("接收者邮箱");
        //发送的标题
        mainMessage.setSubject("嗨喽");
        //发送的内容
        mainMessage.setText("hello world");
        jms.send(mainMessage);
        return "1";

    }
}
