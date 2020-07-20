package com.tokyo.supermix.data.dto;

public class EmailGroupDto {
  private Long id;
  private String name;
  private Long EmailPointsId;
  private boolean schedule;
  private boolean status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isSchedule() {
    return schedule;
  }

  public void setSchedule(boolean schedule) {
    this.schedule = schedule;
  }

  public Long getEmailPointsId() {
    return EmailPointsId;
  }

  public void setEmailPointsId(Long emailPointsId) {
    EmailPointsId = emailPointsId;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }



}
