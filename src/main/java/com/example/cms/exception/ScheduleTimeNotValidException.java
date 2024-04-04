package com.example.cms.exception;

@SuppressWarnings("serial")
public class ScheduleTimeNotValidException extends RuntimeException {

	private String message;

	public ScheduleTimeNotValidException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
