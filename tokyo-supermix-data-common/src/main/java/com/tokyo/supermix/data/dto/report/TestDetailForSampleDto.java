package com.tokyo.supermix.data.dto.report;

import java.util.List;
import com.tokyo.supermix.data.dto.PlantDto;

public class TestDetailForSampleDto {
  private List<TestDetailDto> testDetails;
  private PlantDto plant;
  private IncomingSampleReportDto incomingsample;
 
  public List<TestDetailDto> getTestDetails() {
    return testDetails;
  }
  public void setTestDetails(List<TestDetailDto> testDetails) {
    this.testDetails = testDetails;
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
  
}
