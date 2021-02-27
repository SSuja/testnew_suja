package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MixDesignRatioConfigResponseDto {

  private Long id;
  private String mixDesignCode;
  private Long ratioConfigId;
  private Double value;
  private String ratioConfigName;
}
