package com.tokyo.supermix.data.dto.report;

import java.util.List;

public class ConcreteStrengthDto {
  private String cubeCode;
  private List<DayAndResult> dayAndResult;

  public String getCubeCode() {
    return cubeCode;
  }

  public void setCubeCode(String cubeCode) {
    this.cubeCode = cubeCode;
  }

  public List<DayAndResult> getDayAndResult() {
    return dayAndResult;
  }

  public void setDayAndResult(List<DayAndResult> dayAndResult) {
    this.dayAndResult = dayAndResult;
  }
}
