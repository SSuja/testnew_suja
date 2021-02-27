package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PourDtoResponse {
  private Long id;
  private String name;
  private String description;
  private String projectCode;
  private String projectName;
}
