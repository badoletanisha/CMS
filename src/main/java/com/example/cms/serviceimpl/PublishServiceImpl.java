package com.example.cms.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Publish;
import com.example.cms.entity.Schedule;
import com.example.cms.enums.PostType;
import com.example.cms.exception.BlogPostNotFoundByIdException;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.ScheduleTimeNotValidException;
import com.example.cms.repository.BlogPostRepo;
import com.example.cms.repository.PublishRepo;
import com.example.cms.repository.ScheduleRepo;
import com.example.cms.requestdto.PublishRequest;
import com.example.cms.requestdto.ScheduleRequest;
import com.example.cms.responsedto.PublishResponse;
import com.example.cms.service.PublishService;
import com.example.cms.utility.ResponseStructure;
@Service
public class PublishServiceImpl implements PublishService{



	private PublishRepo publishRepo;
	private BlogPostRepo blogPostRepo;
	private ResponseStructure<PublishResponse>structure;
	private ScheduleRepo scheduleRepo;



	public PublishServiceImpl(PublishRepo publishRepo, BlogPostRepo blogPostRepo,
			ResponseStructure<PublishResponse> structure, ScheduleRepo scheduleRepo) {
		super();
		this.publishRepo = publishRepo;
		this.blogPostRepo = blogPostRepo;
		this.structure = structure;
		this.scheduleRepo = scheduleRepo;
	}

	private Publish mapToPublishEntity(PublishRequest publishRequest,Publish publish) {
		publish.setSeoTitle(publish.getSeoTitle());
		publish.setSeoDescription(publish.getSeoDescription());
		publish.setSeoTopics(publish.getSeoTopics());
		publish.setBlogPost(publish.getBlogPost());
		return publish;

	}

	PublishResponse mapToPublishRespone(Publish publish) {
		PublishResponse publishResponse=new PublishResponse();
		if(publish == null) 
			return publishResponse;
		publishResponse.setSeoTitle(publish.getSeoTitle());
		publishResponse.setSeoDescription(publish.getSeoDescription());
		publishResponse.setSeoTopics(publish.getSeoTopics());
		publishResponse.setBlogPost(publish.getBlogPost());
		return publishResponse;

	}


	@Override
	public ResponseEntity<ResponseStructure<PublishResponse>> publishBlogPost(PublishRequest publishRequest, int postId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return blogPostRepo.findById(postId).map(blogPost->{

			if(!blogPost.getBlog().getUsers().getEmail().equals(email) && !blogPost.getCreatedBy().equals(email))
				throw new IllegalAccessRequestException("Unauthorized User");

			Publish publish =null;
			if(blogPost.getPublish()==null) 
			{
				publish = mapToPublishEntity(publishRequest, blogPost.getPublish());
			}else {
				publish =mapToPublishEntity(publishRequest, new Publish());
			}

			if(publishRequest.getSchedule()!=null)
			{
				if(publishRequest.getSchedule().getDateTime().isAfter(LocalDateTime.now()))
					throw new ScheduleTimeNotValidException("Unable to publish");

				if(publish.getSchedule()==null) {
					publish.setSchedule(scheduleRepo.save(mapToSchedule(publishRequest.getSchedule(),new Schedule())));
					blogPost.setPostType(PostType.SCHEDULED);		
				}else {
					scheduleRepo.save(mapToSchedule(publishRequest.getSchedule(), publish.getSchedule()));
					blogPost.setPostType(PostType.SCHEDULED);
				}
			}

			else {
				blogPost.setPostType(PostType.PUBLISHED);
			}
			publish.setBlogPost(blogPost);
			blogPostRepo.save(blogPost);
			publishRepo.save(publish);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("Blog Published").setBody(mapToPublishRespone(publish)));
		}).orElseThrow(()-> new BlogPostNotFoundByIdException("unable to publish"));
	}


	private Schedule mapToSchedule(ScheduleRequest scheduleRequest, Schedule schedule) {

		schedule.setDateTime(scheduleRequest.getDateTime());
		return schedule;

	}

}





































