package com.example.cms.contoller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.service.ContributionPanelService;
import com.example.cms.utility.ResponseStructure;


@RestController
public class ContributionPanelController {

	private ContributionPanelService  contributionPanelService;



	public ContributionPanelController(ContributionPanelService contributionPanelService) {
		super();
		this.contributionPanelService = contributionPanelService;
	}



	@PostMapping("/users/{userId}/contribution-panels/{panelId}")	
	public ResponseEntity<ResponseStructure<ContributionPanel>> addContributors(@PathVariable int userId,@PathVariable int panelId){
		return contributionPanelService.addContributors(userId,panelId);

	}

	@DeleteMapping("/users/{userId}/contribution-panels/{panelId}")
	public ResponseEntity<ResponseStructure<ContributionPanel>> deleteContribution(@PathVariable int panelId,@PathVariable int userId ) {
		return contributionPanelService.deleteContribution(panelId,userId);

	}

}
