package org.river.article.utils.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final String username = "lichangjiang0490@qq.com";
    private final String password = "ntipgjlzzxpodhba";

    public void sendVerificationCode(String recipientEmail, String verificationCode)
            throws Exception {

        // 设置邮件服务器和SSL
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465"); // QQ邮箱SMTP端口
        props.put("mail.smtp.ssl.enable", "true"); // 启用SSL
        // 创建会话
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // 创建邮件内容
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("YuanHub 提醒您");
        message.setText("您的验证码是: " + verificationCode);

        // 发送邮件
        Transport.send(message);
        System.out.println("验证码邮件发送成功");
    }
}
