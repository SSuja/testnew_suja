package com.tokyo.supermix.data.dto.report;

public class SieveResultAndParameter {
  private String parameter;
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
}
