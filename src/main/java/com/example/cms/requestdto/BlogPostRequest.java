package com.example.cms.requestdto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BlogPostRequest {

	@NotNull
	private String title;
    private String subTitle;
    @Size(message = "should be at least 500 characters length", min = 500,max=5000)
	private String summary;
    
	
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
    
    

}
