package com.example.cms.exception;

@SuppressWarnings("serial")
public class BlogNotFoundByIdExcption extends RuntimeException{
private String message;

public BlogNotFoundByIdExcption(String message) {
	super();
	this.message = message;
}

public String getMessage() {
	return message;
}


}
