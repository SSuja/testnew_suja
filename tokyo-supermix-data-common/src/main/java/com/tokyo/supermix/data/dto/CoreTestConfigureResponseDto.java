package com.tokyo.supermix.data.dto;

import java.util.List;

public class CoreTestConfigureResponseDto {
  public Long testConfigureId;
  public List<CoreTestConfigureMainDto> coreTestConfigureMainDto;

  public Long getTestConfigureId() {
    return testConfigureId;
  }

  public void setTestConfigureId(Long testConfigureId) {
    this.testConfigureId = testConfigureId;
  }

  public List<CoreTestConfigureMainDto> getCoreTestConfigureMainDto() {
    return coreTestConfigureMainDto;
  }

  public void setCoreTestConfigureMainDto(List<CoreTestConfigureMainDto> coreTestConfigureMainDto) {
    this.coreTestConfigureMainDto = coreTestConfigureMainDto;
  }
}
