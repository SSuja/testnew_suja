package com.tokyo.supermix.data.dto.report;

import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;

public class SieveResultAndParameter {
  private String parameter;
  private InputMethod inputMethods;
  private TestParameterType testParameterType;
  private String vale;
  private AcceptedValueForSieveTest acceptedValueForSieveTest;

  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

  public String getVale() {
    return vale;
  }

  public void setVale(String vale) {
    this.vale = vale;
  }

  public AcceptedValueForSieveTest getAcceptedValueForSieveTest() {
    return acceptedValueForSieveTest;
  }

  public void setAcceptedValueForSieveTest(AcceptedValueForSieveTest acceptedValueForSieveTest) {
    this.acceptedValueForSieveTest = acceptedValueForSieveTest;
  }

  public InputMethod getInputMethods() {
    return inputMethods;
  }

  public void setInputMethods(InputMethod inputMethods) {
    this.inputMethods = inputMethods;
  }

  public TestParameterType getTestParameterType() {
    return testParameterType;
  }

  public void setTestParameterType(TestParameterType testParameterType) {
    this.testParameterType = testParameterType;
  }
}
