package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Blog;
import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;

public interface BlogRepo extends JpaRepository<Blog, Integer>{

	boolean existsByTitle(String title);

	boolean existsByUsersAndContributionPanel(User owner, ContributionPanel panel);

	

}
