package com.example.cms.exception;

@SuppressWarnings("serial")
public class BlogPostAlreadyInDraftException extends RuntimeException {
 private String message;

public BlogPostAlreadyInDraftException(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}
 
 
}
