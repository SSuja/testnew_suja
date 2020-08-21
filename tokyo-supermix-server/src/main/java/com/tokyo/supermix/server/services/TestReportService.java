package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.dto.ConcreteTestReportDto;
import com.tokyo.supermix.data.dto.FinishProductTestReportDetailDto;
import com.tokyo.supermix.data.dto.IncomingSampleJasperDeliveryDto;
import com.tokyo.supermix.data.dto.MaterialTestTrialResultDto;
import com.tokyo.supermix.data.dto.report.ConcreteStrengthDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.SeiveTestReportResponseDto;
import com.tokyo.supermix.data.dto.report.SieveTestTrialDto;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;
import com.tokyo.supermix.data.enums.ReportFormat;

public interface TestReportService {

  public TestReportDetailDto getMaterialTestDetailReportPlantWise(String materialTestCode,
      String plantCode);

  public TestReportDetailDto getMaterialTestDetailReport(String materialTestCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleSummaryReportPlantWise(
      String incomingSampleCode, String plantCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleSummaryReport(String incomingSampleCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleDeliveryReportPlantWise(
      String incomingSampleCode, ReportFormat reportFormat, String plantCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleDeliveryReports(String incomingSampleCode,
      ReportFormat reportFormat);

  public ConcreteTestReportDto getConcreteTestReportByPlant(String finishProductTestCode,
      String plantCode);
  public ConcreteTestReportDto getConcreteTestReport(String finishProductTestCode);

  public List<MaterialTestTrialResultDto> getMaterialTestTrailByMaterialTestCode(
      String materialTestCode);

  public List<ConcreteStrengthDto> getConcreteStrengthsByPlant(String plantCode);

  public SeiveTestReportResponseDto getSieveTestReport(String materialTestCode);

  public SeiveTestReportResponseDto getSieveTestReportByPlant(String materialTestCode,
      String plantCode);

  public FinishProductTestReportDetailDto getFinishProductTestDetailReport(
      String finishProductTestCode);

  public List<SieveTestTrialDto> getTrialResultGraph(String materialTestCode);

  public List<ConcreteStrengthDto> getConcreteStrengths();
  
  public IncomingSampleJasperDeliveryDto getIncomingSampleJasperSummaryReport1(
      String incomingSampleCode);

  public IncomingSampleJasperDeliveryDto getIncomingSampleDeliveryReports1(
      String incomingSampleCode, ReportFormat reportFormat);
}
