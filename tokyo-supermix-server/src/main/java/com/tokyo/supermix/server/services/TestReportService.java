package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.dto.report.TestDetailForSampleDto;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;
import com.tokyo.supermix.data.dto.report.TestReportDto;

public interface TestReportService {
  public TestReportDto getMaterialTestReport(String materialTestCode);
  public TestDetailForSampleDto getTestDetails(String incommingSampleCode);
  public TestReportDetailDto getMaterialTestDetailReport(String materialTestCode);
}
