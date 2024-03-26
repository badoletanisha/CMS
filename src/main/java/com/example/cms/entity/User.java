package com.example.cms.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;



@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "users")

@Builder
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String username;
	private String email;
	private String password;
	
	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;
	
	
	@LastModifiedDate
	private LocalDateTime lastModifiedAt;


	public int getUserId() {
		return userId;
	}


	public User setUserId(int userId) {
		this.userId = userId;
		return this;
	}


	public String getEmail() {
		return email;
	}


	public User setEmail(String email) {
		this.email = email;
		return this;
	}


	public String getPassword() {
		return password;
	}


	public User setPassword(String password) {
		this.password = password;
		return this;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public User setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}


	public LocalDateTime getLastModifiedAt() {
		return lastModifiedAt;
	}


	public User setLastModifiedAt(LocalDateTime lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
		return this;
	}


	public String getUsername() {
		return username;
	}


	public User setUsername(String username) {
		this.username = username;
		return this;
	}


	
	
	

	
	
	
	


}
