package com.tokyo.supermix.data.dto.report;

import java.util.List;
import com.tokyo.supermix.data.dto.PlantDto;

public class IncomingSampleDeliveryReportDto {
  private PlantDto plant;
  private IncomingSampleReportDto incomingsample;
  private List<InComingSampleSummaryDto> incomingSampleTestDtos;
  private List<IncomingSampleStatusCount> incomingSampleStatusCounts;
  private SupplierReportDto supplierReportDtos;

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

  public List<InComingSampleSummaryDto> getIncomingSampleTestDtos() {
    return incomingSampleTestDtos;
  }

  public void setIncomingSampleTestDtos(List<InComingSampleSummaryDto> incomingSampleTestDtos) {
    this.incomingSampleTestDtos = incomingSampleTestDtos;
  }

  public List<IncomingSampleStatusCount> getIncomingSampleStatusCounts() {
    return incomingSampleStatusCounts;
  }

  public void setIncomingSampleStatusCounts(
      List<IncomingSampleStatusCount> incomingSampleStatusCounts) {
    this.incomingSampleStatusCounts = incomingSampleStatusCounts;
  }

  public SupplierReportDto getSupplierReportDtos() {
    return supplierReportDtos;
  }

  public void setSupplierReportDtos(SupplierReportDto supplierReportDtos) {
    this.supplierReportDtos = supplierReportDtos;
  }
}
