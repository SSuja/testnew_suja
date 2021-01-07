package com.tokyo.supermix.data.dto.report;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import com.tokyo.supermix.data.dto.PlantDto;

public class StrengthDto {
  private String finishProdutSampleCode;
  private Date testDate;
  private Timestamp updatedDate;
  private String project;
  private String customer;
  private String rawMaterialName;
  private String mixDesignCode;
  private String status;
  private String testName;
  private String dueDay;
  private PlantDto plant;
  private FinishProductResultDto finishProductResult;
  private List<AcceptedValueDtoForStrength> acceptanceCriterias;
  private List<StrengthResultDto> strengthResultDtos;

  public String getFinishProdutSampleCode() {
    return finishProdutSampleCode;
  }

  public void setFinishProdutSampleCode(String finishProdutSampleCode) {
    this.finishProdutSampleCode = finishProdutSampleCode;
  }

  public Date getTestDate() {
    return testDate;
  }

  public void setTestDate(Date testDate) {
    this.testDate = testDate;
  }

  public Timestamp getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Timestamp timestamp) {
    this.updatedDate = timestamp;
  }

  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getRawMaterialName() {
    return rawMaterialName;
  }

  public void setRawMaterialName(String rawMaterialName) {
    this.rawMaterialName = rawMaterialName;
  }

  public String getMixDesignCode() {
    return mixDesignCode;
  }

  public void setMixDesignCode(String mixDesignCode) {
    this.mixDesignCode = mixDesignCode;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public PlantDto getPlant() {
    return plant;
  }

  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }

  public FinishProductResultDto getFinishProductResult() {
    return finishProductResult;
  }

  public void setFinishProductResult(FinishProductResultDto finishProductResult) {
    this.finishProductResult = finishProductResult;
  }

  public List<StrengthResultDto> getStrengthResultDtos() {
    return strengthResultDtos;
  }

  public void setStrengthResultDtos(List<StrengthResultDto> strengthResultDtos) {
    this.strengthResultDtos = strengthResultDtos;
  }

  public List<AcceptedValueDtoForStrength> getAcceptanceCriterias() {
    return acceptanceCriterias;
  }

  public String getDueDay() {
    return dueDay;
  }

  public void setDueDay(String dueDay) {
    this.dueDay = dueDay;
  }

  public void setAcceptanceCriterias(List<AcceptedValueDtoForStrength> acceptanceCriterias) {
    this.acceptanceCriterias = acceptanceCriterias;
  }
}
