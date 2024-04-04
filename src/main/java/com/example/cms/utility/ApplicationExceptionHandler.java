package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cms.exception.BlogAlreadyExistWithThisTitle;
import com.example.cms.exception.BlogNotFoundByIdExcption;
import com.example.cms.exception.BlogPostAlreadyInDraftException;
import com.example.cms.exception.BlogPostNotFoundByIdAndPostTypeByPublishedException;
import com.example.cms.exception.BlogPostNotFoundByIdException;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.PanelNotFoundByIdException;
import com.example.cms.exception.ScheduleTimeNotValidException;
import com.example.cms.exception.TopicNotSpecificationException;
import com.example.cms.exception.UnauthorizedException;
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
	public ResponseEntity<ErrorStructure<String>> userAlreadyExistByEmail(UserAlreadyExistByEmailException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), "User Already exists with the given email Id");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> userNotFound(UserNotFoundException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"User Not Found");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> userAlreadyInPanel(UserAlreadyExistByIdInPanel ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"User Already Exist in Panel");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> userNotExistByIdInPanel(UserNotExistByIdInPanel ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"User Not Exist in Panel");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> blogAlreadyExistWithThisTitle(BlogAlreadyExistWithThisTitle ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"Title already exists with the given userId");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> blogNotFound(BlogNotFoundByIdExcption ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"Blog Not found");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> panelNotFound(PanelNotFoundByIdException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"Panel Not Found");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> topicNotSpecification(TopicNotSpecificationException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"You have to add the topic");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> illegalAccessRequest(IllegalAccessRequestException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"Illegal Access Request");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> unauthorizedException(UnauthorizedException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"UnauthorizedException exception");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> blogPostNotFoundByIdException(BlogPostNotFoundByIdException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"BlogPost Not Exist by id");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> blogPostAlreadyInDraftException(BlogPostAlreadyInDraftException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"Blog Post Alresdy Exist in Draft");
	}

	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> blogPostNotFoundByIdAndPostTypeByPublishedException(BlogPostNotFoundByIdAndPostTypeByPublishedException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"blogpost not  found by id and posttype is published");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> scheduleTimeNotValidException(ScheduleTimeNotValidException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"schedule Time Not Valid");
	}
	
	
	

}
