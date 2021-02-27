package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductTestResponseDto {
  private String code;
  private Status status;
  private FinishProductSampleResponseDto finishProductSample;
  private TestConfigureResponseDto testConfigure;
  private Long noOfTrial;
  private String comments;
  private Date date;
  private String specimenCode;
}
