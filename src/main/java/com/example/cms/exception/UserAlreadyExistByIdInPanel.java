package com.example.cms.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistByIdInPanel extends RuntimeException {
private String message;

public UserAlreadyExistByIdInPanel(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}

}
