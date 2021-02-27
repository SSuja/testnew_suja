package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.FinishProductState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductTrialResponseDto {
  private Long id;
  private Long trialNo;
  private FinishProductState testSample;
  private Date date;
  private Double value;
  private FinishProductTestResponseDto finishProductTest;
  private TestParameterResponseDto testParameter;
}
