package com.tokyo.supermix.data.dto.report;

import java.util.List;
import com.tokyo.supermix.data.dto.PlantDto;

public class TestReportDetailDto {
  private String testName;
  private List<String> equation;
  private PlantDto plant;
  private IncomingSampleReportDto incomingsample;
  private MaterialTestReportDto materialTest;
  private List<TestTrialDto> testTrials;
  private List<TrailValueDto> trailValues;
  private AcceptedValueDto acceptanceCriteria;

  public String getTestName() {
    return testName;
  }

  public void setTestName(String testName) {
    this.testName = testName;
  }

  public List<String> getEquation() {
    return equation;
  }

  public void setEquation(List<String> equation) {
    this.equation = equation;
  }

  public PlantDto getPlant() {
    return plant;
  }

  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }

  public IncomingSampleReportDto getIncomingsample() {
    return incomingsample;
  }

  public void setIncomingsample(IncomingSampleReportDto incomingsample) {
    this.incomingsample = incomingsample;
  }

  public MaterialTestReportDto getMaterialTest() {
    return materialTest;
  }

  public void setMaterialTest(MaterialTestReportDto materialTest) {
    this.materialTest = materialTest;
  }

  public List<TestTrialDto> getTestTrials() {
    return testTrials;
  }

  public void setTestTrials(List<TestTrialDto> testTrials) {
    this.testTrials = testTrials;
  }

  public AcceptedValueDto getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(AcceptedValueDto acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  public List<TrailValueDto> getTrailValues() {
    return trailValues;
  }

  public void setTrailValues(List<TrailValueDto> trailValues) {
    this.trailValues = trailValues;
  }

}
