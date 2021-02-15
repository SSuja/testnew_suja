package com.tokyo.supermix.data.dto.report;

import java.util.Date;
import java.util.List;
import com.tokyo.supermix.data.dto.IncomingSampleResponseDto;
import com.tokyo.supermix.data.dto.PlantDto;

public class SeiveTestReportResponseDto {
  private PlantDto plant;
  private Date testingDate;
  private Date samplingDate;
  private IncomingSampleResponseDto incomingSample;
  private List<SieveTestTrialDto> sieveTestTrial;
  private String test;

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

  public String getTest() {
    return test;
  }

  public void setTest(String test) {
    this.test = test;
  }

  public Date getTestingDate() {
    return testingDate;
  }

  public void setTestingDate(Date testingDate) {
    this.testingDate = testingDate;
  }

  public Date getSamplingDate() {
    return samplingDate;
  }

  public void setSamplingDate(Date samplingDate) {
    this.samplingDate = samplingDate;
  }
}
