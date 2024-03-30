package com.example.cms.serviceimpl;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;
import com.example.cms.exception.IllegalAccessRequestException;
import com.example.cms.exception.PanelNotFoundByIdException;
import com.example.cms.exception.UserAlreadyExistByIdInPanel;
import com.example.cms.exception.UserNotExistByIdInPanel;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.repository.BlogRepo;
import com.example.cms.repository.ContributionPanelRepo;
import com.example.cms.repository.UserRepo;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.utility.ResponseStructure;

@Service
public class ContributionPanelServiceImpl implements ContributionPanelService {

	private ContributionPanelRepo  contributionPanelRepo; 
	private UserRepo userRepo;
	private BlogRepo blogRepo;
	private ResponseStructure<ContributionPanel> structure;
	User owner;
	ContributionPanel panel;


	public ContributionPanelServiceImpl(ContributionPanelRepo contributionPanelRepo,UserRepo userRepo,BlogRepo blogRepo,ResponseStructure<ContributionPanel> structure) {
		super();
		this.contributionPanelRepo = contributionPanelRepo;
		this.userRepo= userRepo;
		this.blogRepo=blogRepo;
		this.structure=structure;
	}

	@Override
	public ResponseEntity<ResponseStructure<ContributionPanel>> addContributors(int userId, int panelId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepo.findByEmail(email).map(owner -> {
			return	contributionPanelRepo.findById(panelId).map(panel ->{
				if(! blogRepo.existsByUsersAndContributionPanel(owner,panel))
					throw new IllegalAccessRequestException("Failed to add Contributer");
				return userRepo.findById(userId).map(contributor -> {
					if(contributionPanelRepo.existsByContributers(contributor))
						throw new UserAlreadyExistByIdInPanel("Contributer present");
					panel.getContributers().add(contributor);
					contributionPanelRepo.save(panel);
					return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
							.setMessage("panel created Successfully")
							.setBody(panel));
				}).orElseThrow(()->new UserNotFoundException("cant delete contributer"));
			}).orElseThrow(()->new PanelNotFoundByIdException("cant delete contributer"));
		}).get();
	}

	@Override
	public ResponseEntity<ResponseStructure<ContributionPanel>> deleteContribution(int panelId, int userId) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return userRepo.findByEmail(email).map(owner -> {
			return	contributionPanelRepo.findById(panelId).map(panel ->{
				if(! blogRepo.existsByUsersAndContributionPanel(owner,panel))
					throw new IllegalAccessRequestException("Failed to add Contributer");
					
				return userRepo.findById(userId).map(contributor -> {
					if(contributionPanelRepo.existsByContributers(contributor))
						throw new UserNotExistByIdInPanel("unable to remove");
					panel.getContributers().remove(contributor);
					contributionPanelRepo.save(panel);
					return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
							.setMessage("Contributor Remove Successfully")
							.setBody(panel));
				}).orElseThrow(()->new UserNotFoundException(" User Not Found "));
			}).orElseThrow(()->new PanelNotFoundByIdException("Panel Not Found"));
		}).get();
		
	}




}
