package com.tokyo.supermix.rest.exception;

public class TokyoSupermixDBException extends TokyoSupermixServerException {

  private static final long serialVersionUID = 1L;

  public TokyoSupermixDBException() {
    super();
  }

  public TokyoSupermixDBException(String message, Integer errorCode) {
    super(message);
    setErrorCode(errorCode);
  }

  public TokyoSupermixDBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public TokyoSupermixDBException(String message, Throwable cause) {
    super(message, cause);
  }

  public TokyoSupermixDBException(String message) {
    super(message);
  }

  public TokyoSupermixDBException(Throwable cause) {
    super(cause);
  }
}
