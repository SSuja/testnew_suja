package com.tokyo.supermix.data.dto.report;

import java.util.List;

public class FinishProductSampleDto {
  private Long finishProductCode;
  private String projectName;
  private Double targetGrade;
  private Double targetSlump;
  private String pourName;
  private List<StrengthResultDto> strengthResults;
  private List<MixDesignProportionDto> mixDesignProportions;
  private List<SlumpTestResult> slumpTestResults;

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

  public String getPourName() {
    return pourName;
  }

  public void setPourName(String pourName) {
    this.pourName = pourName;
  }

  public List<StrengthResultDto> getStrengthResults() {
    return strengthResults;
  }

  public void setStrengthResults(List<StrengthResultDto> strengthResults) {
    this.strengthResults = strengthResults;
  }

  public List<MixDesignProportionDto> getMixDesignProportions() {
    return mixDesignProportions;
  }

  public void setMixDesignProportions(List<MixDesignProportionDto> mixDesignProportions) {
    this.mixDesignProportions = mixDesignProportions;
  }

  public List<SlumpTestResult> getSlumpTestResults() {
    return slumpTestResults;
  }

  public void setSlumpTestResults(List<SlumpTestResult> slumpTestResults) {
    this.slumpTestResults = slumpTestResults;
  }
}
