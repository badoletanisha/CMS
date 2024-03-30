package com.example.cms.exception;

@SuppressWarnings("serial")
public class IllegalAccessRequestException extends RuntimeException {
	private String message;

	public IllegalAccessRequestException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
