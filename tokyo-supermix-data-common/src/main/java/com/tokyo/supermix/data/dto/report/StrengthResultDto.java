package com.tokyo.supermix.data.dto.report;

public class StrengthResultDto {
  private Long age;
  private Double strengthAverage;
  private Double strengthGradeRatio;

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public Double getStrengthAverage() {
    return strengthAverage;
  }

  public void setStrengthAverage(Double strengthAverage) {
    this.strengthAverage = strengthAverage;
  }

  public Double getStrengthGradeRatio() {
    return strengthGradeRatio;
  }

  public void setStrengthGradeRatio(Double strengthGradeRatio) {
    this.strengthGradeRatio = strengthGradeRatio;
  }

}
