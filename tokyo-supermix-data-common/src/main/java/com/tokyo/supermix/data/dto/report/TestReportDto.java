package com.tokyo.supermix.data.dto.report;

import java.util.List;
import com.tokyo.supermix.data.dto.PlantDto;

public class TestReportDto {
  private String testName;
  private String equation;
  private PlantDto plant;
  private IncomingSampleReportDto incomingsample;
  private MaterialTestReportDto materialTest;
  private List<TestTrialReportDto> testTrials;
  private AcceptedValueDto acceptanceCriteria;
  
  public MaterialTestReportDto getMaterialTest() {
    return materialTest;
  }
  public void setMaterialTest(MaterialTestReportDto materialTest) {
    this.materialTest = materialTest;
  }
  public String getTestName() {
    return testName;
  }
  public void setTestName(String testName) {
    this.testName = testName;
  }
  public String getEquation() {
    return equation;
  }
  public void setEquation(String equation) {
    this.equation = equation;
  }
  public IncomingSampleReportDto getIncomingsample() {
    return incomingsample;
  }
  public void setIncomingsample(IncomingSampleReportDto incomingsample) {
    this.incomingsample = incomingsample;
  }
  public PlantDto getPlant() {
    return plant;
  }
  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }
  public List<TestTrialReportDto> getTestTrials() {
    return testTrials;
  }
  public void setTestTrials(List<TestTrialReportDto> testTrials) {
    this.testTrials = testTrials;
  }
  public AcceptedValueDto getAcceptanceCriteria() {
    return acceptanceCriteria;
  }
  public void setAcceptanceCriteria(AcceptedValueDto acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }
  
}
