package com.example.cms.utility;

import org.springframework.stereotype.Component;



@Component

public class ErrorStructure<T> {
	
	
	private int status;
	private String  message;
	private T rootCouse;
	public int getStatus() {
		return status;
	}
	public ErrorStructure<T> setStatus(int status) {
		this.status = status;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	public T getRootCouse() {
		return rootCouse;
	}
	public ErrorStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public ErrorStructure<T> setRootCouse(T rootCouse) {
		this.rootCouse = rootCouse;
		return this;
	}

	


}
