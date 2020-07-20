package com.tokyo.supermix.data.dto;

public class EmailGroupDto {
  private Long id;
  private String name;
  private Long emailPointsId;
  private boolean schedule;
  private boolean status;
  private String plantCode;

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

 
  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Long getEmailPointsId() {
    return emailPointsId;
  }

  public void setEmailPointsId(Long emailPointsId) {
    this.emailPointsId = emailPointsId;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }



}
