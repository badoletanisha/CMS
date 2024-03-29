package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.exception.BlogAlreadyExistWithThisTitle;
import com.example.cms.exception.UserAlreadyExistByEmailException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler{

	private ErrorStructure<String> structure;


	public ApplicationExceptionHandler(ErrorStructure<String> structure) {
		super();
		this.structure = structure;
	}

	private ResponseEntity<ErrorStructure<String>> errorResponse(
			HttpStatus status,String message,String rootCouse){
		return new ResponseEntity<ErrorStructure<String>>(structure.setStatus(status.value())
				.setMessage(message)
				.setRootCouse(rootCouse),status);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistByEmail(UserAlreadyExistByEmailException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User Already exists with the given email Id");

	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogAlreadyExistWithThisTitle(BlogAlreadyExistWithThisTitle ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"Title already exists with the given userId");

	}

}
