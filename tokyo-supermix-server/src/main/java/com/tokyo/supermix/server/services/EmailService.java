package com.tokyo.supermix.server.services;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
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
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}
	public void sendMail(String[] to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
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
	    for(String s:mailAddresses) {
	      mailAddressesString= mailAddressesString+s+",";
	    }
	    return mailAddressesString;
	}
	public void sendMailWithFormat(String[] to, String subject, String body) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() 
	    {
	        public void prepare(MimeMessage mimeMessage) throws Exception 
	        {  
	            mimeMessage.setRecipients(Message.RecipientType.TO,convertStringArraytoString(to) );
	            mimeMessage.setFrom(new InternetAddress("admin@gmail.com"));
	            mimeMessage.setSubject(subject);
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            helper.setText("<html><body>"+body+"</body></html>", true);
	        }
	    };
	    try {
	        mailSender.send(preparator);
	    }
	    catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }
	}
	
}