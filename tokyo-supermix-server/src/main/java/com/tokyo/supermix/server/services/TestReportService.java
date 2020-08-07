package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.dto.ConcreteTestReportDto;
import com.tokyo.supermix.data.dto.FinishProductTestReportDetailDto;
import com.tokyo.supermix.data.dto.MaterialTestTrialResultDto;
import com.tokyo.supermix.data.dto.report.ConcreteStrengthDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.SeiveTestReportResponseDto;
import com.tokyo.supermix.data.dto.report.SieveTestTrialDto;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;

public interface TestReportService {

  public TestReportDetailDto getMaterialTestDetailReport(String materialTestCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleSummaryReport(String incomingSampleCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleDeliveryReports(String incomingSampleCode,
      String testName);

  public ConcreteTestReportDto getConcreteTestReport(String finishProductTestCode);

  public List<MaterialTestTrialResultDto> getMaterialTestTrailByMaterialTestCode(
      String materialTestCode);

  public List<ConcreteStrengthDto> getConcreteStrengths();

  public SeiveTestReportResponseDto getSieveTestReport(String materialTestCode);

  public FinishProductTestReportDetailDto getFinishProductTestDetailReport(
      String finishProductTestCode);

  public List<SieveTestTrialDto> getTrialResultGraph(String materialTestCode);
}
