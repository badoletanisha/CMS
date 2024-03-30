package com.example.cms.exception;

@SuppressWarnings("serial")
public class PanelNotFoundByIdException extends RuntimeException{

	private String message;
	
	public PanelNotFoundByIdException(String message){
		super();
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
}
