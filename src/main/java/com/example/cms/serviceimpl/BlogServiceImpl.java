package com.example.cms.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Blog;
import com.example.cms.entity.ContributionPanel;
import com.example.cms.exception.BlogAlreadyExistWithThisTitle;
import com.example.cms.exception.BlogNotFoundByIdExcption;
import com.example.cms.exception.TopicNotSpecificationException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.repository.BlogRepo;
import com.example.cms.repository.ContributionPanelRepo;
import com.example.cms.repository.UserRepo;
import com.example.cms.requestdto.BlogRequest;
import com.example.cms.responsedto.BlogResponse;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

@Service
public class BlogServiceImpl implements BlogService{

	private BlogRepo blogRepo;
	private UserRepo userRepo;
	private ResponseStructure<BlogResponse> structure;

	private ContributionPanelRepo contributionPanelRepo;

	@Autowired
	public BlogServiceImpl(BlogRepo blogRepo, UserRepo userRepo, ResponseStructure<BlogResponse> structure,
			ContributionPanelRepo contributionPanelRepo) {
		super();
		this.blogRepo = blogRepo;
		this.userRepo = userRepo;
		this.structure = structure;

		this.contributionPanelRepo = contributionPanelRepo;
	}


	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> blog(BlogRequest blogRequest, int userId) {
		return userRepo.findById(userId).map(user -> {
			if (blogRepo.existsByTitle(blogRequest.getTitle())) {
				throw new UserNotFoundException("User Not Found");
			}
			if (blogRequest.getTopics().length < 1) {
				throw new BlogAlreadyExistWithThisTitle("Title is already present. You have to change the title name");
			}

			Blog blog = mapToBlogEntity(blogRequest, new Blog());
			blog.setUsers(user);
			ContributionPanel contributionPanel = contributionPanelRepo.save(new ContributionPanel()) ;
			blog.setContributionPanel(contributionPanel);
			Blog saveblog = blogRepo.save(blog);






			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("blog created Successfully")
					.setBody(mapToBlogResponse(blog)));

		}).orElseThrow(() -> new UserNotFoundException("User Not Found"));


		//		if(!(userRepo.existsById(userId)))
		//			throw new UserNotFoundException("User Not Found");
		////		if(!blogRepo.existsByTitle(blogRequest.getTitle()))
		////			throw new BlogAlreadyExistWithThisTitle("Title is already present You have to change the title name");
		//		Blog blog = blogRepo.save(mapToBlog(blogRequest,new Blog(), userId));
		//		return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
		//				.setMessage("blog created Successfully")
		//				.setBody(mapToBlogrsponse(blog)));

	}



	private Blog mapToBlogEntity(BlogRequest blogRequest, Blog blog) {
		blog.setTitle(blogRequest.getTitle());
		blog.setTopics(blogRequest.getTopics());
		blog.setAbout(blogRequest.getAbout());


		return blog;
	}

	private BlogResponse mapToBlogResponse(Blog blog) {
		BlogResponse blogResponse = new BlogResponse();
		blogResponse.setTitle(blog.getTitle());
		blogResponse.setTopics(blog.getTopics());
		blogResponse.setAbout(blog.getAbout());
		return blogResponse;


	}


	@Override
	public ResponseEntity<Boolean> titleIsPresent(String title) {
		boolean title2 = blogRepo.existsByTitle(title);
		return ResponseEntity.ok(title2);
	}


	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> findBlogById(int blogId) {

		return blogRepo.findById(blogId).map(blog -> 
		ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
				.setMessage("Blog Found")
				.setBody(mapToBlogResponse(blog))))
				.orElseThrow(()-> new BlogNotFoundByIdExcption("Blog Not Found"));
	}





	@Override
	public ResponseEntity<ResponseStructure<BlogResponse>> updateBlog(BlogRequest blogRequest, int blogId) {
		return blogRepo.findById(blogId).map(blog ->{
			if(blogRepo.existsByTitle(blogRequest.getTitle()))
				throw new BlogAlreadyExistWithThisTitle("Failed to create a blog");
			if(blogRequest.getTopics().length<1)
				throw new TopicNotSpecificationException("Failed to create a blog");
			Blog blogEntity = mapToBlogEntity(blogRequest, blog);
			blogRepo.save(blogEntity);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("blog updated Successfully")
					.setBody(mapToBlogResponse(blogEntity)));

		})

				.orElseThrow(()->new BlogNotFoundByIdExcption("blog not found"));



	}




}
