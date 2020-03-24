package com.tokyo.supermix.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import com.tokyo.supermix.util.MailConstants;
 
@Configuration
public class EmailConfig
{
	@Autowired
	private MailConstants mailConstants;
    @Bean
    public SimpleMailMessage emailTemplate()
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailConstants.getAdmin());
        message.setSubject("Important mail");
        return message;
    }
    
}