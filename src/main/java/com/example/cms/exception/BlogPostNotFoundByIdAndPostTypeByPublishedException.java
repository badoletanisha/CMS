package com.example.cms.exception;

@SuppressWarnings("serial")
public class BlogPostNotFoundByIdAndPostTypeByPublishedException extends RuntimeException {
	private String message;

	public BlogPostNotFoundByIdAndPostTypeByPublishedException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}


}
