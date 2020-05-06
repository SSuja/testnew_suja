package com.tokyo.supermix.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * contains  AuthValidations code
 *
 */

@Component
@PropertySource("AuthValidation.properties")
public class AuthValidationConstants {
  @Value("${auth.password.invalid}")
  private String password;
  @Value("${auth.emailOrUserName.invalid}")
  private String emailOrUserName;
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEmailOrUserName() {
    return emailOrUserName;
  }
  public void setEmailOrUserName(String emailOrUserName) {
    this.emailOrUserName = emailOrUserName;
  }
  
  
}
