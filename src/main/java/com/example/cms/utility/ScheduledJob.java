package com.example.cms.utility;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.cms.entity.BlogPost;
import com.example.cms.enums.PostType;
import com.example.cms.repository.BlogPostRepo;

@Component
public class ScheduledJob {
	private BlogPostRepo blogPostRepo;

	public ScheduledJob(BlogPostRepo blogPostRepo) {
		super();
		this.blogPostRepo = blogPostRepo;
	}

	@Scheduled(fixedDelay = 60*10001)
	public void publishScheduledBlogPost() {
    List<BlogPost> posts =  blogPostRepo.findAllByPublishScheduleDateTimeLessThanEqualAndPostType(LocalDateTime.now(),PostType.SCHEDULED)
	.stream().map(post ->{
		post.setPostType(PostType.PUBLISHED);
		return post;
		
	}).collect(Collectors.toList());
    blogPostRepo.saveAll(posts);
	
	}
}
