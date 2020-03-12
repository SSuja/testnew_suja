package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Status;

public class ConcreteTestResponseDto {
  private Long id;
  private String mixDesignCode;
  private Double slump;
  private String temperature;
  private Double waterContent;
  private Double slumpGradeRatio;
  private Double waterCementRatio;
  private Double waterBinderRatio;
  private Status status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMixDesignCode() {
    return mixDesignCode;
  }

  public void setMixDesignCode(String mixDesignCode) {
    this.mixDesignCode = mixDesignCode;
  }

  public Double getSlump() {
    return slump;
  }

  public void setSlump(Double slump) {
    this.slump = slump;
  }

  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public Double getWaterContent() {
    return waterContent;
  }

  public void setWaterContent(Double waterContent) {
    this.waterContent = waterContent;
  }

  public Double getSlumpGradeRatio() {
    return slumpGradeRatio;
  }

  public void setSlumpGradeRatio(Double slumpGradeRatio) {
    this.slumpGradeRatio = slumpGradeRatio;
  }

  public Double getWaterCementRatio() {
    return waterCementRatio;
  }

  public void setWaterCementRatio(Double waterCementRatio) {
    this.waterCementRatio = waterCementRatio;
  }

  public Double getWaterBinderRatio() {
    return waterBinderRatio;
  }

  public void setWaterBinderRatio(Double waterBinderRatio) {
    this.waterBinderRatio = waterBinderRatio;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
