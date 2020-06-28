package com.tokyo.supermix.data.dto;

import java.util.List;

public class ConcreteTestReportDto {

  private String projectName;
  private String customerName;
  private double targetGrade;
  private double targetSlump;
  private String dateOfTesting;
  private String dateOfCasting;
  private Long ageOfCubeTest;
  private double strengthGradeRatio;
  private List<CubeTestReportDto> CubeTestReports;

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public double getTargetGrade() {
    return targetGrade;
  }

  public void setTargetGrade(double targetGrade) {
    this.targetGrade = targetGrade;
  }

  public double getTargetSlump() {
    return targetSlump;
  }

  public void setTargetSlump(double targetSlump) {
    this.targetSlump = targetSlump;
  }

  public String getDateOfTesting() {
    return dateOfTesting;
  }

  public void setDateOfTesting(String dateOfTesting) {
    this.dateOfTesting = dateOfTesting;
  }

  public String getDateOfCasting() {
    return dateOfCasting;
  }

  public void setDateOfCasting(String dateOfCasting) {
    this.dateOfCasting = dateOfCasting;
  }

  public Long getAgeOfCubeTest() {
    return ageOfCubeTest;
  }

  public void setAgeOfCubeTest(Long ageOfCubeTest) {
    this.ageOfCubeTest = ageOfCubeTest;
  }

  public List<CubeTestReportDto> getCubeTestReports() {
    return CubeTestReports;
  }

  public void setCubeTestReports(List<CubeTestReportDto> cubeTestReports) {
    CubeTestReports = cubeTestReports;
  }

  public double getStrengthGradeRatio() {
    return strengthGradeRatio;
  }

  public void setStrengthGradeRatio(double strengthGradeRatio) {
    this.strengthGradeRatio = strengthGradeRatio;
  }
}