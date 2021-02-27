package com.tokyo.supermix.data.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MixDesignTestConfigDetailsDto {
  private RatioConfigResponseDto ratioConfigResponseDto;
  private List<RatioConfigParameterResponseDto> ratioConfigParameterResponseDto;
  private List<RatioConfigEquationResponseDto> ratioConfigEquationResponseDto;
}
