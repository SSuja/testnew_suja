package com.tokyo.supermix.data.dto;

import java.util.List;

public class ConcreteTestReportDto {
  private String reportNo;
  private String projectName;
  private String address;
  private String faxNumber;
  private String plantName;
  private String customerName;
  private double targetGrade;
  private double targetSlump;
  private String dateOfTesting;
  private String dateOfCasting;
  private Long ageOfCubeTest;
  private double averageStrength;
  private List<CubeTestReportDto> CubeTestReports;
  public String getReportNo() {
    return reportNo;
  }
  public void setReportNo(String reportNo) {
    this.reportNo = reportNo;
  }
  public String getProjectName() {
    return projectName;
  }
  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getFaxNumber() {
    return faxNumber;
  }
  public void setFaxNumber(String faxNumber) {
    this.faxNumber = faxNumber;
  }
  public String getPlantName() {
    return plantName;
  }
  public void setPlantName(String plantName) {
    this.plantName = plantName;
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
  public double getAverageStrength() {
    return averageStrength;
  }
  public void setAverageStrength(double averageStrength) {
    this.averageStrength = averageStrength;
  }
  public List<CubeTestReportDto> getCubeTestReports() {
    return CubeTestReports;
  }
  public void setCubeTestReports(List<CubeTestReportDto> cubeTestReports) {
    CubeTestReports = cubeTestReports;
  }
}
