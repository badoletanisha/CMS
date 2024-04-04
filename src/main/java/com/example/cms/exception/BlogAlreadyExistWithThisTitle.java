package com.example.cms.exception;

@SuppressWarnings("serial")
public class BlogAlreadyExistWithThisTitle extends RuntimeException {
private String message;

public BlogAlreadyExistWithThisTitle(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}


}
