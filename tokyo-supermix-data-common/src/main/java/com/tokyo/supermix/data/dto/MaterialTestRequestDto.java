package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialTestRequestDto {
  private String code;
  private Long noOfTrial;
  private Status status;
  private String incomingSampleCode;
  private Long testConfigureId;
  private String comment;
}
