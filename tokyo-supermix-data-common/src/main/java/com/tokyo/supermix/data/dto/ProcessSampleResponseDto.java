package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProcessSampleResponseDto {
  private String code;
  private Long quantity;
  private Long unitId;
  private String unit;
  private Long rawMaterialId;
  private String rawMaterialName;
  private IncomingSampleResponseDto incomingSample;
  private String createdAt;
  private String updatedAt;
}
