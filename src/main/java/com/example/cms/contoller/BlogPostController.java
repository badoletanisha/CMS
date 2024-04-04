package com.example.cms.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestdto.BlogPostRequest;
import com.example.cms.responsedto.BlogPostResponse;
import com.example.cms.service.BlogPostService;
import com.example.cms.utility.ResponseStructure;

@RestController
public class BlogPostController {

	private BlogPostService blogPostService;


	public BlogPostController(BlogPostService blogPostService) {
		super();
		this.blogPostService = blogPostService;
	}

	@PostMapping("/blog/{blogId}/blogpost")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPost(@RequestBody BlogPostRequest blogPostRequest,
			@PathVariable int blogId){
		return blogPostService.createBlogPost(blogPostRequest, blogId);

	}

	@PutMapping("/blog-post/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateBlogPost(BlogPostRequest blogPostRequest, int blogId){
		return blogPostService.updateBlogPost(blogPostRequest, blogId);

	}

	@DeleteMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(@PathVariable int postId){
		return blogPostService.deleteBlogPost(postId);

	}
	
	@PutMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>>  updateUnpublishBlogPostType(@PathVariable int postId){
		return blogPostService.updateUnpublishBlogPostType(postId);
		
	}
	@GetMapping("/blog-post/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPost(@PathVariable int postId){
		return blogPostService.getBlogPost(postId);
		
	}
	@GetMapping("/blog-posts/{postId}")
	public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPostByPublish(@PathVariable int postId){
		return blogPostService.getBlogPostByPublish(postId);
		
	}
}
