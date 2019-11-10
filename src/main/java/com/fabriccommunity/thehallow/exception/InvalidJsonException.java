package com.fabriccommunity.thehallow.exception;

public class InvalidJsonException extends RuntimeException {
	private static final long serialVersionUID = -7857427222095572275L;
	
	public InvalidJsonException(String message) {
		super(message);
	}
}
