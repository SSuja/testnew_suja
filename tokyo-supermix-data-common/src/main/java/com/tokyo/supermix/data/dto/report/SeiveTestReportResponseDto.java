package com.tokyo.supermix.data.dto.report;

import java.util.List;
import com.tokyo.supermix.data.dto.IncomingSampleResponseDto;
import com.tokyo.supermix.data.dto.PlantDto;

public class SeiveTestReportResponseDto {
  private PlantDto plant;
  private IncomingSampleResponseDto incomingSample;
  private List<SieveTestTrialDto> sieveTestTrial;
  public PlantDto getPlant() {
    return plant;
  }
  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }
  public IncomingSampleResponseDto getIncomingSample() {
    return incomingSample;
  }
  public void setIncomingSample(IncomingSampleResponseDto incomingSample) {
    this.incomingSample = incomingSample;
  }
  public List<SieveTestTrialDto> getSieveTestTrial() {
    return sieveTestTrial;
  }
  public void setSieveTestTrial(List<SieveTestTrialDto> sieveTestTrial) {
    this.sieveTestTrial = sieveTestTrial;
  }
}