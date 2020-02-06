package com.tokyo.supermix.rest.exception;

public class TokyoSupermixForbiddenException extends TokyoSupermixServerException {

  private static final long serialVersionUID = 1L;

  public TokyoSupermixForbiddenException() {
    super();
  }

  public TokyoSupermixForbiddenException(Integer errorCode) {
    super();
    setErrorCode(errorCode);
  }

  public TokyoSupermixForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }

}
