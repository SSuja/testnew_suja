package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.dto.report.AdmixtureTestReportDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;

public interface TestReportService {

  public TestReportDetailDto getMaterialTestDetailReport(String materialTestCode);

  public TestReportDetailDto getCementDetailReport(String materialTestCode);

  public AdmixtureTestReportDto getAdmixtureReport(String materialTestCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleSummaryReport(String incomingSampleCode);

  public IncomingSampleDeliveryReportDto getIncomingSampleDeliveryReports(String incomingSampleCode,
      String testName);
}
