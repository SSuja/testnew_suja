package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.report.FinishProductSampleDto;
import com.tokyo.supermix.data.dto.report.FinishProductSampleResultReportDto;

public interface FinishProductResultReportService {
  public FinishProductSampleResultReportDto getFinishProductSampleResultReportByFinishProductSampleId(
      Long finishProductSampleId);

  public List<FinishProductSampleDto> getAllFinishProductSamples();
}
