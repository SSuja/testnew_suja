package com.tokyo.supermix.data.dto.report;

import java.sql.Date;
import java.util.List;
import com.tokyo.supermix.data.dto.IncomingSampleResponseDto;
import com.tokyo.supermix.data.dto.PlantDto;

public class SeiveTestReportResponseDto {
  private PlantDto plant;
  private IncomingSampleReportDto incomingSample;
  private List<SieveTestTrialDto> sieveTestTrial;
  private Date testingDate;

  public PlantDto getPlant() {
    return plant;
  }

  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }

  public List<SieveTestTrialDto> getSieveTestTrial() {
    return sieveTestTrial;
  }

  public void setSieveTestTrial(List<SieveTestTrialDto> sieveTestTrial) {
    this.sieveTestTrial = sieveTestTrial;
  }

  public Date getTestingDate() {
    return testingDate;
  }

  public void setTestingDate(Date testingDate) {
    this.testingDate = testingDate;
  }

  public IncomingSampleReportDto getIncomingSample() {
    return incomingSample;
  }

  public void setIncomingSample(IncomingSampleReportDto incomingSample) {
    this.incomingSample = incomingSample;
  }
}
