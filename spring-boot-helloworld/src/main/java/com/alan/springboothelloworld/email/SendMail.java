package com.alan.springboothelloworld.email;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件。。第一种方式
 * 发送邮箱步骤
 * 1. 配置基本信息
 * 2. 获取授权码：
 *      a.登陆发送人的账号邮箱，获取客户端授权密码（需要短信验证）
 */
public class SendMail {
    public static void main(String[] a){
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        // 指定验证为true
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout", "1000");
        // 验证账号及密码，密码对应邮箱授权码（163密码可行，qq必须授权码）
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("发送者邮箱", "授权码"); // 帐户名，授权码
            }
        };
        Session session = Session.getInstance(props, auth);

        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);
        // 设置发送者
        try {
            // 设置发送方式与接收者
            message.setFrom(new InternetAddress("发送者邮箱"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("接收者邮箱"));
            // 设置主题
            message.setSubject("邮件发送测试");
            // 设置内容
            message.setContent("Hello,Alan!", "text/html;charset=utf-8");

            // 3.创建 Transport用于将邮件发送
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }
}
