package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.entities.Unit;

public class TestParameterResponseDto {
  private Long id;
  private TestResponseDto test;
  private Parameter parameter;

  public Parameter getParameter() {
    return parameter;
  }

  public void setParameter(Parameter parameter) {
    this.parameter = parameter;
  }

  public Unit getUnit() {
    return Unit;
  }

  public void setUnit(Unit unit) {
    Unit = unit;
  }

  private Unit Unit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TestResponseDto getTest() {
    return test;
  }

  public void setTest(TestResponseDto test) {
    this.test = test;
  }



}
