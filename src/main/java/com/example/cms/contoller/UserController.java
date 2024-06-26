package com.example.cms.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;



@RestController

public class UserController {

	private UserService userservice;

	 public UserController(UserService userService) {
	        this.userservice = userService;
	    }

	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> userRegistration(@RequestBody UserRequest userRequest){
		return userservice.userRegistration(userRequest);

	}
	
	@GetMapping("/test")
	public String tst() {
		return "hellow from cms";
	}
	
	@GetMapping("/users/findUserById/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findUser(@PathVariable("userId") int userId){
		return userservice.findUser(userId);
		
	}
	
	
	@DeleteMapping("/users/deleteByUserId/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteByUserId(@PathVariable("userId") int UserId){
		return userservice.deleteByUserId(UserId);
	}
}
