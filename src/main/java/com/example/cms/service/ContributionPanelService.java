package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.utility.ResponseStructure;

public interface ContributionPanelService {

	ResponseEntity<ResponseStructure<ContributionPanel>> addContributors(int userId, int panelId);

	ResponseEntity<ResponseStructure<ContributionPanel>> deleteContribution(int panelId, int userId);

}
