package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountMaterialDto {
  private String materialName;
  private int passCount;
  private int failCount;
  private int newCount;
  private int processCount;
  private int total;
}
