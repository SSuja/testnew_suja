package com.tokyo.supermix.data.dto.report;

import java.util.List;
import com.tokyo.supermix.data.dto.PlantDto;

public class SieveTestReportDto {
  private PlantDto plant;
  private IncomingSampleReportDto incomingsample;
  private List<SieveSizeDto> sieveSizes;
  private SieveAcceptedValueDto SeiveAcceptedValueDto;
  private List<SieveTestTrialDto> sieveTestTrials;
  private SieveTestDto sieveTest;
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
  public List<SieveSizeDto> getSieveSizes() {
    return sieveSizes;
  }
  public void setSieveSizes(List<SieveSizeDto> sieveSizes) {
    this.sieveSizes = sieveSizes;
  }
  public SieveAcceptedValueDto getSeiveAcceptedValueDto() {
    return SeiveAcceptedValueDto;
  }
  public void setSeiveAcceptedValueDto(SieveAcceptedValueDto seiveAcceptedValueDto) {
    SeiveAcceptedValueDto = seiveAcceptedValueDto;
  }
  public List<SieveTestTrialDto> getSieveTestTrials() {
    return sieveTestTrials;
  }
  public void setSieveTestTrials(List<SieveTestTrialDto> sieveTestTrials) {
    this.sieveTestTrials = sieveTestTrials;
  }
  public SieveTestDto getSieveTest() {
    return sieveTest;
  }
  public void setSieveTest(SieveTestDto sieveTest) {
    this.sieveTest = sieveTest;
  }
}
