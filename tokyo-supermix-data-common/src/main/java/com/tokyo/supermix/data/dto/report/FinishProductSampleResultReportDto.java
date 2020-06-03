package com.tokyo.supermix.data.dto.report;

import java.util.List;

public class FinishProductSampleResultReportDto {
  private List<StrengthResultDto> StrengthResults;
  private String projectName;
  private Double targetGrade;
  private Double targetSlump;
  private String temperature;
  private Double waterContent;
  private Double slumpValue;
  private Double slumpGradeRatio;
  private Double waterCementRatio;
  private Double waterBinderRatio;
  public List<StrengthResultDto> getStrengthResults() {
    return StrengthResults;
  }
  public void setStrengthResults(List<StrengthResultDto> strengthResults) {
    StrengthResults = strengthResults;
  }
  public String getProjectName() {
    return projectName;
  }
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }
  public Double getTargetGrade() {
    return targetGrade;
  }
  public void setTargetGrade(Double targetGrade) {
    this.targetGrade = targetGrade;
  }
  public Double getTargetSlump() {
    return targetSlump;
  }
  public void setTargetSlump(Double targetSlump) {
    this.targetSlump = targetSlump;
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
}
