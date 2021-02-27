package com.tokyo.supermix.data.dto;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductTrialRequestDto {
  private Long id;
  private Long trialNo;
  private Date date;
  private Double value;
  private String finishProductTestCode;
  private Long testParameterId;
  private String dateValue;
}
