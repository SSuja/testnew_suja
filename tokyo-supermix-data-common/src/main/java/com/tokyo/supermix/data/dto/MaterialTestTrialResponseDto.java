package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MaterialTestTrialResponseDto {
  private String code;
  private Long trialNo;
  private MaterialTestResponseDto materialTest;
  private String createdAt;
}
