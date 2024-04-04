package com.example.cms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
public class UserAlreadyExistByEmailException extends RuntimeException{

	@SuppressWarnings("unused")
	private String message;
	
	public UserAlreadyExistByEmailException(String message) {
		this.message=message;
	}

	
}
