package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.ConcreteTestReportDto;
import com.tokyo.supermix.data.dto.MaterialTestTrialResultDto;
import com.tokyo.supermix.data.dto.report.AdmixtureTestReportDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.SieveTestReportDto;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;

public interface TestReportService {

  public TestReportDetailDto getMaterialTestDetailReport(String materialTestCode);

  public TestReportDetailDto getCementDetailReport(String materialTestCode);

  public AdmixtureTestReportDto getAdmixtureReport(String materialTestCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleSummaryReport(String incomingSampleCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleDeliveryReports(String incomingSampleCode,
      String testName);

  public SieveTestReportDto getSieveTestReport(String materialTestCode);

  public ConcreteTestReportDto getConcreteTestReport(String finishProductTestCode);

  public List<MaterialTestTrialResultDto> getMaterialTestTrailByMaterialTestCode(
      String materialTestCode);
}
