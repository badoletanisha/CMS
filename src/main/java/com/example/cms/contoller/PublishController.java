package com.example.cms.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.requestdto.PublishRequest;
import com.example.cms.responsedto.PublishResponse;
import com.example.cms.service.PublishService;
import com.example.cms.utility.ResponseStructure;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class PublishController {

	private PublishService publishService;
	
	@PostMapping("/blog-posts/{postId}/publishes")
	public ResponseEntity<ResponseStructure<PublishResponse>> publishBlogPost(@RequestBody PublishRequest publishRequest,@PathVariable int postId){
		return publishService.publishBlogPost(publishRequest,postId);
		
	}
	
//	@PutMapping("/blog-posts/{postId}")
//	public ResponseEntity<ResponseStructure<PublishResponse>> updateBlogPost(@PathVariable int postId) {
//		
//		return publishService.updateBlogPost(postId);
//	}
}
