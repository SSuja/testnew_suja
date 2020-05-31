package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.report.ConcreteStrengthTestDto;
import com.tokyo.supermix.data.dto.report.TestDetailForSampleDto;
import com.tokyo.supermix.data.dto.report.TestReportDetailDto;
import com.tokyo.supermix.data.dto.report.TestReportDto;

public interface TestReportService {
  public TestReportDto getMaterialTestReport(String materialTestCode);

  public TestDetailForSampleDto getTestDetails(String incommingSampleCode);

  public TestReportDetailDto getMaterialTestDetailReport(String materialTestCode);

  public List<ConcreteStrengthTestDto> getStrengthResult(String concreteTestType,
      String concreteTestName);
}
