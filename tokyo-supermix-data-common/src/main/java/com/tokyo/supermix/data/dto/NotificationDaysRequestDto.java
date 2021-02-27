package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationDaysRequestDto {
  private Long id;
  private Long emailGroupId;
  private Double days;
}
