package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailGroupResponseDto {
  private Long id;
  private String name;
  private EmailPointsResponseDto emailPoints;
  private boolean schedule;
  private boolean status;
  private PlantDto plant;
}
