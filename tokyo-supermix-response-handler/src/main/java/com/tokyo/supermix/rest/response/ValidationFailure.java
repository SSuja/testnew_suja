package com.tokyo.supermix.rest.response;

public class ValidationFailure {
  public ValidationFailure(String field, String code) {
    this.field = field;
    this.code = code;
  }

  private String field;

  private String code;

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

}
