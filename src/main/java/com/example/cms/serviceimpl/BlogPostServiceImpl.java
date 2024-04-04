package com.example.cms.serviceimpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.BlogPost;
import com.example.cms.enums.PostType;
import com.example.cms.exception.BlogNotFoundByIdExcption;
import com.example.cms.exception.BlogPostAlreadyInDraftException;
import com.example.cms.exception.BlogPostNotFoundByIdAndPostTypeByPublishedException;
import com.example.cms.exception.BlogPostNotFoundByIdException;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.repository.BlogPostRepo;
import com.example.cms.repository.BlogRepo;
import com.example.cms.repository.ContributionPanelRepo;
import com.example.cms.repository.UserRepo;
import com.example.cms.requestdto.BlogPostRequest;
import com.example.cms.responsedto.BlogPostResponse;
import com.example.cms.service.BlogPostService;
import com.example.cms.utility.ResponseStructure;

@Service
public class BlogPostServiceImpl implements BlogPostService{

	private BlogPostRepo blogPostRepo;
	private BlogRepo blogRepo;
	private UserRepo userRepo;
	private ContributionPanelRepo contributionPanelRepo;
	private PublishServiceImpl publishServiceImpl;
	private ResponseStructure<BlogPostResponse> responseStructure;

	//	@Autowired
	public BlogPostServiceImpl(BlogPostRepo blogPostRepo, BlogRepo blogRepo, UserRepo userRepo,
			ContributionPanelRepo contributionPanelRepo, PublishServiceImpl publishServiceImpl,
			ResponseStructure<BlogPostResponse> responseStructure) {
		super();
		this.blogPostRepo = blogPostRepo;
		this.blogRepo = blogRepo;
		this.userRepo = userRepo;
		this.contributionPanelRepo = contributionPanelRepo;
		this.publishServiceImpl = publishServiceImpl;
		this.responseStructure = responseStructure;
	}


	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> createBlogPost(BlogPostRequest blogPostRequest,int blogId) 
	{
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepo.findByEmail(email).map(user->{
			return blogRepo.findById(blogId).map(blog->{
				if(!blog.getUsers().getEmail().equals(email)&& !contributionPanelRepo.existsByPanelIdAndContributers(blog.getContributionPanel().getPanelId(), user))
					throw new IllegalAccessRequestException("unautherize");
				BlogPost blogPost = mapToBlogPostEntity(blogPostRequest, new BlogPost());
				blogPost.setPostType(PostType.DRAFT);
				blogPost.setBlog(blog);
				blogPostRepo.save(blogPost);
				return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value()).setMessage("").setBody(mapToBlogPostResponse(blogPost)));
			}).orElseThrow(()->new BlogNotFoundByIdExcption("Not Found"));
		}).get();
	}





	private BlogPost mapToBlogPostEntity(BlogPostRequest blogPostRequest, BlogPost blogPost) {
		blogPost.setTitle(blogPostRequest.getTitle());
		blogPost.setSubTitle(blogPostRequest.getSubTitle());
		blogPost.setSummary(blogPostRequest.getSummary());
		return blogPost;
	}
	private BlogPostResponse  mapToBlogPostResponse(BlogPost blogPost)
	{
		BlogPostResponse blogPostResponse =new BlogPostResponse();
		blogPostResponse.setTitle(blogPost.getTitle());
		blogPostResponse.setSubTitle(blogPost.getSubTitle());
		blogPostResponse.setPostType(blogPost.getPostType());
		blogPostResponse.setSummary(blogPost.getSummary());
		
		blogPostResponse.setPublishResponse(publishServiceImpl.mapToPublishRespone(blogPost.getPublish()));
		blogPostResponse.setBlog(blogPost.getBlog());
		return blogPostResponse;

	}

	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateBlogPost(BlogPostRequest blogPostRequest,int blogPostId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepo.findByEmail(email).map(user->{
			return blogPostRepo.findById(blogPostId).map(blogPost->{
				return blogRepo.findById(blogPost.getBlog().getBlogId()).map(blog->{
					if(!blog.getUsers().getEmail().equals(email)&&!contributionPanelRepo.existsByPanelIdAndContributers(blog.getContributionPanel().getPanelId(),user))
						throw new IllegalAccessRequestException("Illegal Access");
					BlogPost exBlogPost = mapToBlogPostEntity(blogPostRequest,blogPost);
					blogPostRepo.save(exBlogPost);
					return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value()).setMessage("BLogPost Updated").setBody(mapToBlogPostResponse(exBlogPost)));
				}).orElseThrow(()->new BlogNotFoundByIdExcption("Blog not found"));
			}).orElseThrow(()-> new BlogNotFoundByIdExcption("Blog Post Not Found"));
		}).get();
	}


	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> deleteBlogPost(int postId) {
		String email =SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepo.findByEmail(email).map(user->{
			return blogPostRepo.findById(postId).map(blogPost->{
				if(!blogPost.getBlog().getUsers().getEmail().equals(email) && !blogPost.getCreatedBy().equals(email))
					throw new IllegalAccessRequestException("unauthorized user");
				blogPostRepo.delete(blogPost);
				return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value()).setMessage("BLogPost delete successfully").setBody(mapToBlogPostResponse(blogPost)));
			}).orElseThrow(()-> new BlogNotFoundByIdExcption("unable to delete"));

		}).get();

	}


	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> updateUnpublishBlogPostType(int postId) {			String email = SecurityContextHolder.getContext().getAuthentication().getName();
	String email1 = SecurityContextHolder.getContext().getAuthentication().getName();
	return userRepo.findByEmail(email1).map(user-> {
		return blogPostRepo.findById(postId).map(blogPost->{
			if(!blogPost.getBlog().getUsers().getEmail().equals(email1) && !blogPost.getCreatedBy().equals(email1)) 
				throw new BlogPostAlreadyInDraftException("cant unpublish");
			blogPost.setPostType(PostType.DRAFT);
			blogPostRepo.save(blogPost);
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value()).setMessage("blogpost unpublished successfully").setBody(mapToBlogPostResponse(blogPost)));
		}).orElseThrow(()->new BlogNotFoundByIdExcption("cant unpublished"));
	}).get();
	}


	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPost(int postId) {
		return blogPostRepo.findById(postId).map(blogPost->{
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Post Found").setBody(mapToBlogPostResponse(blogPost)));
		}).orElseThrow(()-> new BlogPostNotFoundByIdException("not found"));
	}


	@Override
	public ResponseEntity<ResponseStructure<BlogPostResponse>> getBlogPostByPublish(int postId) {
		return blogPostRepo.findByPostIdAndPostType(postId,PostType.PUBLISHED).map(Post->{
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Post Found").setBody(mapToBlogPostResponse(Post)));
		}).orElseThrow(()-> new BlogPostNotFoundByIdAndPostTypeByPublishedException("not found"));
	}








}
