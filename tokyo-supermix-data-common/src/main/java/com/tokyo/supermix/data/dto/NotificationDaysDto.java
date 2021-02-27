package com.tokyo.supermix.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationDaysDto {
  private Long id;
  private String emailGroupName;
  private Double days;
}
