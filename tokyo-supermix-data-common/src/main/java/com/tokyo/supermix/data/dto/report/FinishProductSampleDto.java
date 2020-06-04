package com.tokyo.supermix.data.dto.report;

public class FinishProductSampleDto {
  private Long finishProductCode;
  private String projectName;
  private Double targetGrade;
  private Double targetSlump;

  public Long getFinishProductCode() {
    return finishProductCode;
  }

  public void setFinishProductCode(Long finishProductCode) {
    this.finishProductCode = finishProductCode;
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

}
