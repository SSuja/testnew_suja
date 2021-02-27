package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialTestResponseDto {
  private String code;
  private String createdAt;
  private String updatedAt;
  private Long noOfTrial;
  private String status;
  private IncomingSampleResponseDto incomingSample;
  private TestConfigureResponseDto testConfigure;
  private String specimenCode;
  private String comment;
}
