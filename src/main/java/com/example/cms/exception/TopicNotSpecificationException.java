package com.example.cms.exception;

public class TopicNotSpecificationException extends RuntimeException {
	private String message;

	public TopicNotSpecificationException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
