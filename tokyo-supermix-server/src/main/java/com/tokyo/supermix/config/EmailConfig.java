package com.tokyo.supermix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig {
  @Autowired
  private MailNotifyConstants mailNotifyConstants;

  @Bean
  public SimpleMailMessage emailTemplate() {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(mailNotifyConstants.getAdmin());
    message.setSubject("Important mail");
    return message;
  }

}
