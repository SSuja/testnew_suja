package com.tokyo.supermix.data.dto;

public class NotificationDaysRequestDto {
  private Long id;
  private Long emailGroupId;
  private Double days;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getEmailGroupId() {
    return emailGroupId;
  }

  public void setEmailGroupId(Long emailGroupId) {
    this.emailGroupId = emailGroupId;
  }

  public Double getDays() {
    return days;
  }

  public void setDays(Double days) {
    this.days = days;
  }
}
