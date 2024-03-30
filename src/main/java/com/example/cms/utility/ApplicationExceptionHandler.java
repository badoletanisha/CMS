package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.exception.BlogAlreadyExistWithThisTitle;
import com.example.cms.exception.BlogNotFoundByIdExcption;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.PanelNotFoundByIdException;
import com.example.cms.exception.TopicNotSpecificationException;
import com.example.cms.exception.UserAlreadyExistByEmailException;
import com.example.cms.exception.UserAlreadyExistByIdInPanel;
import com.example.cms.exception.UserNotExistByIdInPanel;
import com.example.cms.exception.UserNotFoundException;


@RestControllerAdvice

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

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleBlogNotFound(BlogNotFoundByIdExcption ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"Blog Not found");

	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserNotFound(UserNotFoundException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"User Not Found");

	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handlePanelNotFound(PanelNotFoundByIdException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"Panel Not Found");

	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleTopicNotSpecification(TopicNotSpecificationException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"You have to add the topic");

	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleIllegalAccessRequest(IllegalAccessRequestException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"Illegal Access Request");

	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyInPanel(UserAlreadyExistByIdInPanel ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"User Already Exist in Panel");

	}


	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> userNotExistByIdInPanel(UserNotExistByIdInPanel ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"User Not Exist in Panel");

	}
	
}
