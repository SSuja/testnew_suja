package com.tokyo.supermix.server.services;

import java.io.FileNotFoundException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
  @Autowired
  private JavaMailSender mailSender;
  @Autowired
  private SimpleMailMessage preConfiguredMessage;

  public void sendMail(String to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(preConfiguredMessage.getFrom());
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);
    mailSender.send(message);
  }

  public void sendMail(String[] to, String subject, String body) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(preConfiguredMessage.getFrom());
    message.setTo(to);
    message.setSubject(subject);
    message.setText(body);
    mailSender.send(message);
  }

  public void sendPreConfiguredMail(String message) {
    SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
    mailMessage.setText(message);
    mailSender.send(mailMessage);
  }

  private String convertStringArraytoString(String[] mailAddresses) {
    String mailAddressesString = "";
    for (String s : mailAddresses) {
      mailAddressesString = mailAddressesString + s + ",";
    }
    return mailAddressesString;
  }

  public void sendEmailWithAttachment(String[] toAddress, String subject, String message,
      byte[] byteArray,String filename) throws MessagingException, FileNotFoundException {
    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      public void prepare(MimeMessage mimeMessage) throws Exception {
        mimeMessage.setRecipients(Message.RecipientType.TO, convertStringArraytoString(toAddress));
        mimeMessage.setSubject(subject);
        mimeMessage.setFrom(preConfiguredMessage.getFrom());
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        final InputStreamSource attachment = new ByteArrayResource(byteArray);
        helper.addAttachment(filename, attachment);
        helper.setText("<html><body>" + message + "</body></html>", true);
      }
    };
    try {
      mailSender.send(preparator);
    } catch (MailException ex) {
      System.err.println(ex.getMessage());
    }
  }
  
  public void sendMailWithFormat(String[] to, String subject, String body) {
    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      public void prepare(MimeMessage mimeMessage) throws Exception {
        mimeMessage.setRecipients(Message.RecipientType.TO, convertStringArraytoString(to));
        mimeMessage.setSubject(subject);
        mimeMessage.setFrom(preConfiguredMessage.getFrom());
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setText("<html><body>"+ body + "</body></html>", true);
      }
    };
    try {
      mailSender.send(preparator);
    } catch (MailException ex) {
      System.err.println(ex.getMessage());
    }
  }
  
}
