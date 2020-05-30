package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.dto.report.TestReportDto;

public interface TestReportService {
  public TestReportDto getMaterialTestReport(String materialTestCode);
}
