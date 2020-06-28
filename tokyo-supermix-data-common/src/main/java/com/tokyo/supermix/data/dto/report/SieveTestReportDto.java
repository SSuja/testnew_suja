package com.tokyo.supermix.data.dto.report;

import java.util.List;
import com.tokyo.supermix.data.dto.PlantDto;

public class SieveTestReportDto {
  private String testName;
  private PlantDto plant;
  private IncomingSampleReportDto incomingsample;
  private MaterialTestReportDto materialTest;
  private List<TrailValueDto> trailValues;
  private AcceptedValueDto acceptanceCriteria;
  private List<SieveSizeDto> sieveSizes;

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

  public List<TrailValueDto> getTrailValues() {
    return trailValues;
  }

  public void setTrailValues(List<TrailValueDto> trailValues) {
    this.trailValues = trailValues;
  }

  public AcceptedValueDto getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(AcceptedValueDto acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  public List<SieveSizeDto> getSieveSizes() {
    return sieveSizes;
  }

  public void setSieveSizes(List<SieveSizeDto> sieveSizes) {
    this.sieveSizes = sieveSizes;
  }
}
