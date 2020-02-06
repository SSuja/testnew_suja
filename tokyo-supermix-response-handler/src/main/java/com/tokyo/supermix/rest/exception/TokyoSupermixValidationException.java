package com.tokyo.supermix.rest.exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class TokyoSupermixValidationException extends Exception {
  private static final long serialVersionUID = 1L;
  private Integer errorCode;
  private List<FieldError> fieldErrors;

  public TokyoSupermixValidationException() {
    super();
  }

  public TokyoSupermixValidationException(Integer errorCode, List<FieldError> fieldErrors) {
    super();
    setErrorCode(errorCode);
  }

  public TokyoSupermixValidationException(Integer errorCode) {
    super();
    setErrorCode(errorCode);
  }

  public TokyoSupermixValidationException(String message, Integer errorCode) {
    super(message);
    setErrorCode(errorCode);
  }

  public TokyoSupermixValidationException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public TokyoSupermixValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public TokyoSupermixValidationException(String message) {
    super(message);
  }

  public TokyoSupermixValidationException(Throwable cause) {
    super(cause);
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

public List<FieldError> getFieldErrors() {
	return fieldErrors;
}

public void setFieldErrors(List<FieldError> fieldErrors) {
	this.fieldErrors = fieldErrors;
}

}
