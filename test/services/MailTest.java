package services;

import com.jsnu.yls.graduation.plugin.task.MailTask;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件相关测试
 * <p>
 * Created by WeiXY on 2016/2/5.
 */
public class MailTest {

    private ApplicationContext applicationContext;
    private MailTask mailTask;

    {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        mailTask = (MailTask) applicationContext.getBean("mailTask");
    }

    @Test
    public void testSend() {
        mailTask.sendMail();
    }

}
