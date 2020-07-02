package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.entities.EmailGroup;

public class NotificationDaysResponseDto {
  private Long id;
  private EmailGroup emailGroup;
  private Double days;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EmailGroup getEmailGroup() {
    return emailGroup;
  }

  public void setEmailGroup(EmailGroup emailGroup) {
    this.emailGroup = emailGroup;
  }

  public Double getDays() {
    return days;
  }

  public void setDays(Double days) {
    this.days = days;
  }
}
