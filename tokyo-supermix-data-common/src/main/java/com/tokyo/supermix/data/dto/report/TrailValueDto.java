package com.tokyo.supermix.data.dto.report;

import java.util.List;

public class TrailValueDto {
  private String parameterName;
  private List<Double> values;
  public String getParameterName() {
    return parameterName;
  }
  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }
  public List<Double> getValues() {
    return values;
  }
  public void setValues(List<Double> values) {
    this.values = values;
  }
  
}
