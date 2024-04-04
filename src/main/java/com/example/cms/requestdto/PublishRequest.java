package com.example.cms.requestdto;

import jakarta.validation.constraints.NotNull;

public class PublishRequest {

	
	@NotNull(message = "Invalid Input")
	private String seoTitle;
	private String seoDescription;
	private String[] seoTopics;
	private ScheduleRequest schedule;
	
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
	public ScheduleRequest getSchedule() {
		return schedule;
	}
	public void setSchedule(ScheduleRequest schedule) {
		this.schedule = schedule;
	}
	
	
}
