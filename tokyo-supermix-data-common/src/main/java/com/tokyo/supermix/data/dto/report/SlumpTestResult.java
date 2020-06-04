package com.tokyo.supermix.data.dto.report;

public class SlumpTestResult {
  private Double slumpValue;
  private Double slumpGradeRatio;
  private Double waterCementRatio;
  private Double waterBinderRatio;
  private String temperature;
  private Double waterContent;

  public Double getSlumpValue() {
    return slumpValue;
  }

  public void setSlumpValue(Double slumpValue) {
    this.slumpValue = slumpValue;
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
}
