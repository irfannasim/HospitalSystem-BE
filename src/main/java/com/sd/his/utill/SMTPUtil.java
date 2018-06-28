package com.sd.his.utill;

import com.sd.his.model.EmailConfiguration;
import com.sd.his.service.EmailConfigurationService;
import com.sd.his.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/*
 * @author    : Irfan Nasim
 * @Date      : 11-Jun-18
 * @version   : ver. 1.0.0
 *
 * ________________________________________________________________________________________________
 *
 *  Developer				Date		     Version		Operation		Description
 * ________________________________________________________________________________________________
 *
 *
 * ________________________________________________________________________________________________
 *
 * @Project   : HIS
 * @Package   : com.sd.his.utill
 * @FileName  : SMTPUtil
 *
 * Copyright ©
 * SolutionDots,
 * All rights reserved.
 *
 */
public class SMTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private Session session;

    @Autowired
    EmailConfigurationService emailConfigurationService;

    public SMTPUtil() {
    }

    public SMTPUtil(EmailConfiguration config) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.user", config.getSenderEmail().trim());
            props.put("mail.smtp.host", config.getSmtpHost().trim());
            props.put("mail.smtp.port", config.getSmtpPort().trim());
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", config.getSmtpPort().trim());
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(config.getSenderEmail().trim(), config.getSmptPassword());
                }
            };
            session = Session.getInstance(props, auth);
        } catch (Exception ex) {
            logger.error("Error creating AmazonEmailClient: ", ex.fillInStackTrace());
        }
    }

    public Boolean sendEmail(String emailSender, String emailRecipient, String emailSubject, String emailHtmlBody) {

        try {
            Message msg = new MimeMessage(session);
            msg.setContent(emailHtmlBody, "text/html");
            msg.setSubject(emailSubject);
            msg.setFrom(new InternetAddress(emailSender));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailRecipient));
            Transport.send(msg);
            logger.info("Send Email INFO:  successfully");
            return true;
        } catch (Exception ex) {
            logger.error("Sending Email INFO: Error occurred while sending.!", ex.fillInStackTrace());
            return false;
        }
    }

}
