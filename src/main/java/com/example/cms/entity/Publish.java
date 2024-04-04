package com.example.cms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Publish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int publishId;
	@NotNull(message = "Invalid Input")
	private String seoTitle;
	private String seoDescription;
	private String[] seoTopics;
	
	@OneToOne(mappedBy = "publish")
	private BlogPost blogPost;

	@OneToOne
	private Schedule schedule;
	
	
	public int getPublishId() {
		return publishId;
	}

	public void setPublishId(int publishId) {
		this.publishId = publishId;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public String[] getSeoTopics() {
		return seoTopics;
	}

	public void setSeoTopics(String[] seoTopics) {
		this.seoTopics = seoTopics;
	}

	public BlogPost getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	
	
	
	
	
}
