package com.example.cms.responsedto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserResponse {

	private int userId;
	private String username;
	private String email;
	private LocalDateTime createdAt; 
	private LocalDateTime lastModifiedAt;



	public int getUserId() {
		return userId;
	}

	public UserResponse setUserId(int userId) {
		this.userId = userId;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public UserResponse setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserResponse setEmail(String email) {
		this.email = email;
		return this;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public UserResponse setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public LocalDateTime getLastModifiedAt() {
		return lastModifiedAt;
	}

	public UserResponse setLastModifiedAt(LocalDateTime lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
		return this;
	}


	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private Long userId;
		private String username;
		private String email;

		private Builder() {}

		public Builder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}
	}
}
