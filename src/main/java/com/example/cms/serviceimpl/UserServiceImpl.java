package com.example.cms.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.cms.entity.User;
import com.example.cms.exception.UserAlreadyExistByEmailException;
import com.example.cms.repository.UserRepo;
import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;




@Service
public class UserServiceImpl implements UserService{


	private UserRepo userRepo;

	ResponseStructure<UserResponse> structure;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepo userRepo, ResponseStructure<UserResponse> structure,
			PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.structure = structure;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> userRegistration(UserRequest userRequest) {
		if(userRepo.existsByEmail(userRequest.getEmail()))

			throw new UserAlreadyExistByEmailException("Failed to register User");

		User user = userRepo.save(mapToUserEntity(userRequest, new User()));

		return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
				.setMessage("User registered successfully")
				.setBody(mapToUserResponse(user)));
	}



	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setEmail(userRequest.getEmail());
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return user;
	}



	private UserResponse mapToUserResponse(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getUserId());
		userResponse.setUsername(user.getUsername());
		userResponse.setEmail(user.getEmail());
		return userResponse;


	}			




}















