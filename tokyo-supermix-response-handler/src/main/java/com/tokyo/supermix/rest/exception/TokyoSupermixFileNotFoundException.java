package com.tokyo.supermix.rest.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TokyoSupermixFileNotFoundException extends TokyoSupermixServerException {
	private static final long serialVersionUID = 1L;

	public TokyoSupermixFileNotFoundException(String message) {
		super(message);
	}

	public TokyoSupermixFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
