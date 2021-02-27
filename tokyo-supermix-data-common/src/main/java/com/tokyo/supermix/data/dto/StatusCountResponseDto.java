package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StatusCountResponseDto {
  private int total;
  private int passCount;
  private int newCount;
  private int processCount;
  private int failCount;
}
