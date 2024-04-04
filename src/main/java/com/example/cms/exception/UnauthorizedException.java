package com.example.cms.exception;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {
private String message;

public UnauthorizedException(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}

}
