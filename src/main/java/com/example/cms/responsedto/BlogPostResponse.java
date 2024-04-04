package com.example.cms.responsedto;



import com.example.cms.entity.Blog;
import com.example.cms.enums.PostType;


public class BlogPostResponse {

	private int postId;
	private String title;
	private String subTitle;
	private String summary;
	private PostType postType;
	
	private Blog blog;
	
	private PublishResponse publishResponse;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
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
	public PostType getPostType() {
		return postType;
	}
	public void setPostType(PostType postType) {
		this.postType = postType;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public PublishResponse getPublishResponse() {
		return publishResponse;
	}
	public void setPublishResponse(PublishResponse publishResponse) {
		this.publishResponse = publishResponse;
	}
	

}
