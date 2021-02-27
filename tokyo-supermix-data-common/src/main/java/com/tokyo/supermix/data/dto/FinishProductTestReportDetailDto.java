package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductTestReportDetailDto {
  private String finishProductSampleCode;
  private Long tesconfigurationId;
  private Long materialStateId;
}
