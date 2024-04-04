package com.example.cms.exception;

@SuppressWarnings("serial")
public class BlogPostNotFoundByIdException extends RuntimeException{
private String message;

public BlogPostNotFoundByIdException(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}

}
