package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.Origin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestOriginDto {
  private Long testId;
  private Long testConfigureId;
  private String testName;
  private Origin testOrigin;
  private boolean coreTest;
}
