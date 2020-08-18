package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.dto.report.IncomingSampleReportDto;
import com.tokyo.supermix.data.dto.report.SupplierReportDto;

public class IncomingSampleJasperDeliveryDto {
  private PlantDto plant;
  private IncomingSampleReportDto incomingsample;
  private List<IncomingSampleJasperTestDto> incomingSampleTestDtos;
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

  public SupplierReportDto getSupplierReportDtos() {
    return supplierReportDtos;
  }

  public void setSupplierReportDtos(SupplierReportDto supplierReportDtos) {
    this.supplierReportDtos = supplierReportDtos;
  }

  public List<IncomingSampleJasperTestDto> getIncomingSampleTestDtos() {
    return incomingSampleTestDtos;
  }

  public void setIncomingSampleTestDtos(List<IncomingSampleJasperTestDto> incomingSampleTestDtos) {
    this.incomingSampleTestDtos = incomingSampleTestDtos;
  }

}

