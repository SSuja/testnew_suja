package com.tokyo.supermix.data.dto;

public class EmailGroupResponseDto {
  private Long id;
  private String name;
  private EmailPointsResponseDto emailPoints;
  private boolean schedule;
  private boolean status;
  private PlantDto plant;

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

  public EmailPointsResponseDto getEmailPoints() {
    return emailPoints;
  }

  public void setEmailPoints(EmailPointsResponseDto emailPoints) {
    this.emailPoints = emailPoints;
  }

  public PlantDto getPlant() {
    return plant;
  }

  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }
}
