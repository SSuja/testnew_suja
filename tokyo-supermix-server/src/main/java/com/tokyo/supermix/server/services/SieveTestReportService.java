package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.dto.report.SieveTestReportDto;

public interface SieveTestReportService {
  public SieveTestReportDto getSeiveTestReport(String seiveTestCode);
}
