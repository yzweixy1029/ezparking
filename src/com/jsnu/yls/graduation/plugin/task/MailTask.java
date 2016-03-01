package com.jsnu.yls.graduation.plugin.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件任务
 *
 * Created by WeiXY on 2016/2/25.
 */

@Component
public class MailTask {

    @Autowired
    private JavaMailSenderImpl mailSender;

    public void sendMail() {
        String title = "魏氏停车场通知";
        String to = "yzweixy1029@qq.com";
        String content = "<html><body><h4>感谢您到本停车场停车</h4>\n" +
                "<h5>本次停车时长：<span style=\"color: red\">32</span>分钟</h5>\n" +
                "<h5>本次停车费用：<span style=\"color: red\">5</span>元</h5>\n" +
                "<h5>欢迎您下次光临！</h5>\n" +
                "<h6>------------------------------------</h6>\n" +
                "<h6 style=\"margin-left: 200px\">魏氏停车场</h6></body></html>";

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(title);
            helper.setTo(to);
            helper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(message);
    }

}
