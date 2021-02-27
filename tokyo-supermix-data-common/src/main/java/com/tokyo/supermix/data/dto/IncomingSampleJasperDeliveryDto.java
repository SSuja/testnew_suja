package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.dto.report.IncomingSampleReportDto;
import com.tokyo.supermix.data.dto.report.SupplierReportDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IncomingSampleJasperDeliveryDto {
  private PlantDto plant;
  private IncomingSampleReportDto incomingsample;
  private List<IncomingSampleJasperTestDto> incomingSampleTestDtos;
  private SupplierReportDto supplierReportDtos;
}
