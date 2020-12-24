package com.tokyo.supermix.rest.exception;

public class TokyoSupermixFileStorageException extends TokyoSupermixServerException {
	private static final long serialVersionUID = 1L;

	public TokyoSupermixFileStorageException(String message) {
		super(message);
	}

	public TokyoSupermixFileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
