package com.example.cms.exception;

@SuppressWarnings("serial")
public class UserNotExistByIdInPanel extends RuntimeException {
private String message;

public UserNotExistByIdInPanel(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}


}
