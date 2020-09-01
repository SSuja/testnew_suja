package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;

public class FinishProductTestTrialDto {
  private Long trialNo;
  private double value;
  private InputMethod testParameterInputMethods;
  private TestParameterType testParameterType;
  private Long finishProductTestNoOfTrial;

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public InputMethod getTestParameterInputMethods() {
    return testParameterInputMethods;
  }

  public void setTestParameterInputMethods(InputMethod testParameterInputMethods) {
    this.testParameterInputMethods = testParameterInputMethods;
  }

  public TestParameterType getTestParameterType() {
    return testParameterType;
  }

  public void setTestParameterType(TestParameterType testParameterType) {
    this.testParameterType = testParameterType;
  }

  public Long getFinishProductTestNoOfTrial() {
    return finishProductTestNoOfTrial;
  }

  public void setFinishProductTestNoOfTrial(Long finishProductTestNoOfTrial) {
    this.finishProductTestNoOfTrial = finishProductTestNoOfTrial;
  }
}
