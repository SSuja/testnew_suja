package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailGroupDto {
  private Long id;
  private String name;
  private Long emailPointsId;
  private boolean schedule;
  private boolean status;
  private String plantCode;
}
