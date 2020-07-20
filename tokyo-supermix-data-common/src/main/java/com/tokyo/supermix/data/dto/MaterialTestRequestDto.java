package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestLevel;

public class MaterialTestRequestDto {
  private String code;
  private Long noOfTrial;
  private Status status;
  private TestLevel testLevel;
  private String incomingSampleCode;
  private Long testConfigureId;
  private Long materialStateId;
  private String comment;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getNoOfTrial() {
    return noOfTrial;
  }

  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public TestLevel getTestLevel() {
    return testLevel;
  }

  public void setTestLevel(TestLevel testLevel) {
    this.testLevel = testLevel;
  }

  public String getIncomingSampleCode() {
    return incomingSampleCode;
  }

  public void setIncomingSampleCode(String incomingSampleCode) {
    this.incomingSampleCode = incomingSampleCode;
  }

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }

  public Long getMaterialStateId() {
    return materialStateId;
  }

  public void setMaterialStateId(Long materialStateId) {
    this.materialStateId = materialStateId;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
