package com.tokyo.supermix.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mail.properties")
public class MailNotifyConstants {
  @Value("${mail.notify.admin}")
  private String admin;

  @Value("${mail.notify.report}")
  private String[] mailReportUser;

  public String getAdmin() {
    return admin;
  }

  public void setAdmin(String admin) {
    this.admin = admin;
  }

  public String[] getMailReportUser() {
    return mailReportUser;
  }

  public void setMailReportUser(String[] mailReportUser) {
    this.mailReportUser = mailReportUser;
  }
}
