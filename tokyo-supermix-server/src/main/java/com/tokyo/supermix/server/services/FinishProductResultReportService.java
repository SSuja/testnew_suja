package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.dto.report.FinishProductAllResultsDto;
import com.tokyo.supermix.data.dto.report.FinishProductSampleResultReportDto;

public interface FinishProductResultReportService {
  public FinishProductSampleResultReportDto getFinishProductSampleResultReportByFinishProductSampleId(
      Long finishProductSampleId);

  public FinishProductAllResultsDto getAllResults();
}
