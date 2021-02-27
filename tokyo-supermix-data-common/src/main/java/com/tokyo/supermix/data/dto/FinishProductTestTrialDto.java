package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.TestParameterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductTestTrialDto {
  private Long trialNo;
  private double value;
  private InputMethod testParameterInputMethods;
  private TestParameterType testParameterType;
  private Long finishProductTestNoOfTrial;
  private String testParameterParameterName;
  private String testParameterAbbreviation;
  private ParameterDataType testParameterParameterParameterDataType;
  private String dateValue;
}
