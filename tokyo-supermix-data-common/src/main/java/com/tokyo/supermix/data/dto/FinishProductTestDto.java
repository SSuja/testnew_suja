package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FinishProductTestDto {
  private Status status;
  private Long testConfigId;
  private String finishproductTestCode;
  private String createdDate;
  private String updatedDate;
  private String testName;
  private String finishProductSampleCode;
  private MainType mainType;
  private Long rawMaterialId;
  private String specimenCode;
}
