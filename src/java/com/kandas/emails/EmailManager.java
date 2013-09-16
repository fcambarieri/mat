/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kandas.emails;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author fcambarieri
 */
public class EmailManager {

    private static String from = "mat@kandas.com";

    public static void sendEmail(String subject, String to, String body) {
        try {
            Properties props = new Properties();

            props.put("mail.smtp.host", "localhost");


            Session session = Session.getInstance(props, null);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // If the desired charset is known, you can use
            // setText(text, charset)
            msg.setText(body);

            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
