package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.MixDesignField;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestParameterType;

public class FinishProductTestResultDto {
  private double result;
  private Long finishProductTestNoOfTrial;
  private Long finishProductTestTestConfigureId;
  private Status finishProductTestStatus;
  private String finishProductTestTestConfigureTestName;
  private String finishProductTestFinishProductSampleCode;
  private Long finishProductTestMaterialStateId;
  private String testParameterParameterName;
  private InputMethod testParameterInputMethods;
  private TestParameterType testParameterTypeType;
  private MixDesignField testParameterMixDesignField;
  private Date finishProductTestDate;
  public double getResult() {
    return result;
  }
  public void setResult(double result) {
    this.result = result;
  }
  public Long getFinishProductTestNoOfTrial() {
    return finishProductTestNoOfTrial;
  }
  public void setFinishProductTestNoOfTrial(Long finishProductTestNoOfTrial) {
    this.finishProductTestNoOfTrial = finishProductTestNoOfTrial;
  }
  public Long getFinishProductTestTestConfigureId() {
    return finishProductTestTestConfigureId;
  }
  public void setFinishProductTestTestConfigureId(Long finishProductTestTestConfigureId) {
    this.finishProductTestTestConfigureId = finishProductTestTestConfigureId;
  }
  public Status getFinishProductTestStatus() {
    return finishProductTestStatus;
  }
  public void setFinishProductTestStatus(Status finishProductTestStatus) {
    this.finishProductTestStatus = finishProductTestStatus;
  }
  public String getFinishProductTestTestConfigureTestName() {
    return finishProductTestTestConfigureTestName;
  }
  public void setFinishProductTestTestConfigureTestName(
      String finishProductTestTestConfigureTestName) {
    this.finishProductTestTestConfigureTestName = finishProductTestTestConfigureTestName;
  }
  public String getFinishProductTestFinishProductSampleCode() {
    return finishProductTestFinishProductSampleCode;
  }
  public void setFinishProductTestFinishProductSampleCode(
      String finishProductTestFinishProductSampleCode) {
    this.finishProductTestFinishProductSampleCode = finishProductTestFinishProductSampleCode;
  }
  public Long getFinishProductTestMaterialStateId() {
    return finishProductTestMaterialStateId;
  }
  public void setFinishProductTestMaterialStateId(Long finishProductTestMaterialStateId) {
    this.finishProductTestMaterialStateId = finishProductTestMaterialStateId;
  }
  public String getTestParameterParameterName() {
    return testParameterParameterName;
  }
  public void setTestParameterParameterName(String testParameterParameterName) {
    this.testParameterParameterName = testParameterParameterName;
  }
  public InputMethod getTestParameterInputMethods() {
    return testParameterInputMethods;
  }
  public void setTestParameterInputMethods(InputMethod testParameterInputMethods) {
    this.testParameterInputMethods = testParameterInputMethods;
  }
  public TestParameterType getTestParameterTypeType() {
    return testParameterTypeType;
  }
  public void setTestParameterTypeType(TestParameterType testParameterTypeType) {
    this.testParameterTypeType = testParameterTypeType;
  }
  public MixDesignField getTestParameterMixDesignField() {
    return testParameterMixDesignField;
  }
  public void setTestParameterMixDesignField(MixDesignField testParameterMixDesignField) {
    this.testParameterMixDesignField = testParameterMixDesignField;
  }
  public Date getFinishProductTestDate() {
    return finishProductTestDate;
  }
  public void setFinishProductTestDate(Date finishProductTestDate) {
    this.finishProductTestDate = finishProductTestDate;
  }

}
