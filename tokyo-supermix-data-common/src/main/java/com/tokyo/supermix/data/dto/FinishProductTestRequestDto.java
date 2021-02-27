package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductTestRequestDto {
  private String code;
  private Status status;
  private String finishProductSampleCode;
  private Long testConfigureId;
  private Long noOfTrial;
  private Date date;
  private String specimenCode;
  private String comments;
}
