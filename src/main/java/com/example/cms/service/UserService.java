package com.example.cms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.utility.ResponseStructure;

public interface UserService {

	
	
	
	
	ResponseEntity<ResponseStructure<UserResponse>> userRegistration(UserRequest userRequest);

	

}
