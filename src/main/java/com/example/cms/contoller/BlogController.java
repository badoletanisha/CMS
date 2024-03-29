package com.example.cms.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.entity.Blog;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BlogController {
	private BlogService blogService;
	
	public BlogController(BlogService blogService) {
		super();
		this.blogService = blogService;
	}


	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponse>> blog( @RequestBody BlogRequest blogRequest,@PathVariable int userId ){
		return blogService.blog(blogRequest,userId);

	}
	
	@GetMapping("/title/{title}/blogs")
	public ResponseEntity<Boolean> titleIsPresent(@PathVariable  String title) {
		return  blogService.titleIsPresent(title);
	}
	
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(@PathVariable  int blogId) {
		return  blogService.findBlogById(blogId);
	}
	
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(@RequestBody BlogRequest blogRequest,@PathVariable int blogId) {
		return blogService.updateBlog(blogRequest,blogId);
	}
	
}
