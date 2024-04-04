package com.example.cms.responsedto;

import org.springframework.stereotype.Component;

import com.example.cms.entity.BlogPost;
import com.example.cms.entity.Schedule;


@Component
public class PublishResponse {

	
	private int publishId;
	private String seoTitle;
	private String seoDescription;
	private String[] seoTopics;
	private Schedule schedule;
	
	private BlogPost blogPost;
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
