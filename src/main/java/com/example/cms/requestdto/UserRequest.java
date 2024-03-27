package com.example.cms.requestdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequest {
	
	private int userId;
	
	private String username;
	@NotNull(message="must not be null")
	@Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message="Invalid emailId")
	private String email;
	@NotNull(message = "must not be null")
	@Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	private String password;
	
	
	public int getUserId() {
		return userId;
	}
	public UserRequest setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public UserRequest setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public UserRequest setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public UserRequest setUsername(String username) {
		this.username = username;
		return this;
	}
	
	
	
	
	
	
}
