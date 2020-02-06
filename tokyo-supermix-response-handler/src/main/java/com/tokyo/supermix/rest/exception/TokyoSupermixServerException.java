package com.tokyo.supermix.rest.exception;

public class TokyoSupermixServerException extends Exception {

	private static final long serialVersionUID = 1L;
	private Integer errorCode;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public TokyoSupermixServerException() {
		super();
	}

	public TokyoSupermixServerException(String message, Integer errorCode) {
		super(message);
		setErrorCode(errorCode);
	}

	public TokyoSupermixServerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TokyoSupermixServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokyoSupermixServerException(String message) {
		super(message);
	}

	public TokyoSupermixServerException(Throwable cause) {
		super(cause);
	}

}
