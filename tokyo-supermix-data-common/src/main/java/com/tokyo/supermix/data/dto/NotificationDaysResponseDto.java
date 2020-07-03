package com.tokyo.supermix.data.dto;

import java.util.List;

public class NotificationDaysResponseDto {
  private String emailGroupName;
  private List<Double> days;

  public String getEmailGroupName() {
    return emailGroupName;
  }

  public void setEmailGroupName(String emailGroupName) {
    this.emailGroupName = emailGroupName;
  }

  public List<Double> getDays() {
    return days;
  }

  public void setDays(List<Double> days) {
    this.days = days;
  }
}
