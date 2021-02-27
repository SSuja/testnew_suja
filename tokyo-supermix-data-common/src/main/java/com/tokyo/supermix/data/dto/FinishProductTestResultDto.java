package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestParameterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductTestResultDto {
  private double result;
  private Long finishProductTestNoOfTrial;
  private Long finishProductTestTestConfigureId;
  private Status finishProductTestStatus;
  private String finishProductTestTestConfigureTestName;
  private String finishProductTestFinishProductSampleCode;
  private Long finishProductTestMaterialStateId;
  private String testParameterParameterName;
  private InputMethod testParameterInputMethods;
  private TestParameterType testParameterTypeType;
  private Date finishProductTestDate;
  private AcceptedType finishProductTestTestConfigureTestAcceptedType;
  private Long finishProductTestFinishProductSampleMixDesignRawMaterialId;
  private String finishProductTestCode;
  private String finishProductTestFinishProductSampleMixDesignRawMaterialName;
  private String testParameterAbbreviation;
  private String finishProductTestFinishProductSampleFinishProductCode;
  private ReportFormat finishProductTestTestConfigureReportFormat;
  private Long finishProductTestFinishProductSampleMixDesignRawMaterialMaterialSubCategoryId;
  private String finishProductTestComments;
  private boolean finishProductTestApproved;
}
