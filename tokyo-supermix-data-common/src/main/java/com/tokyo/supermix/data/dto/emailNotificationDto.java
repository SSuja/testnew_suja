package com.tokyo.supermix.data.dto;

public class emailNotificationDto {
  private Long id;
  private String emailGroupName;
  private Double days;
  private Long emailGroupId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmailGroupName() {
    return emailGroupName;
  }

  public void setEmailGroupName(String emailGroupName) {
    this.emailGroupName = emailGroupName;
  }

  public Double getDays() {
    return days;
  }

  public void setDays(Double days) {
    this.days = days;
  }

  public Long getEmailGroupId() {
    return emailGroupId;
  }

  public void setEmailGroupId(Long emailGroupId) {
    this.emailGroupId = emailGroupId;
  }



}
