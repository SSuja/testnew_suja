package com.tokyo.supermix.data.dto.report;

import java.sql.Date;

public class ConcreteStrengthTestDto {
  private Date date;
  private Long age;
  private Double result;
  private String concreteTestName;
  private Long cubeCode;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }

  public String getConcreteTestName() {
    return concreteTestName;
  }

  public void setConcreteTestName(String concreteTestName) {
    this.concreteTestName = concreteTestName;
  }

  public Long getCubeCode() {
    return cubeCode;
  }

  public void setCubeCode(Long cubeCode) {
    this.cubeCode = cubeCode;
  }

}
