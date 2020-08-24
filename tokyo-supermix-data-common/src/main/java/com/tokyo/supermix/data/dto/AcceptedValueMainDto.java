package com.tokyo.supermix.data.dto;

import java.util.List;

public class AcceptedValueMainDto {
  private TestConfigureResDto testConfigureResDto;
  private List<AccepetedValueDto> accepetedValueDto;

  public TestConfigureResDto getTestConfigureResDto() {
    return testConfigureResDto;
  }

  public void setTestConfigureResDto(TestConfigureResDto testConfigureResDto) {
    this.testConfigureResDto = testConfigureResDto;
  }

  public List<AccepetedValueDto> getAccepetedValueDto() {
    return accepetedValueDto;
  }

  public void setAccepetedValueDto(List<AccepetedValueDto> accepetedValueDto) {
    this.accepetedValueDto = accepetedValueDto;
  }

}
