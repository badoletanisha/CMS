package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.requestdto.BlogPostRequest;
import com.example.cms.responsedto.BlogPostResponse;
import com.example.cms.utility.ResponseStructure;

public interface BlogPostService {

	
public	ResponseEntity<ResponseStructure<BlogPostResponse>> updateBlogPost(BlogPostRequest blogPostRequest, int blogPostId);

public ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPost(BlogPostRequest blogPostRequest, int blogId);

public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(int postId);


public ResponseEntity<ResponseStructure<BlogPostResponse>> updateUnpublishBlogPostType(int postId);

public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPost(int postId);

public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPostByPublish(int postId);

	

}
