package com.fabriccommunity.spookytime.exception;

public class InvalidJsonException extends RuntimeException {
	private final String message;

	public InvalidJsonException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getLocalizedMessage() {
		return message;
	}
}


