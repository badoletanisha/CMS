package com.example.cms.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


@EntityListeners(value = AuditingEntityListener.class)
@Entity
public class ContributionPanel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int panelId;

	@ManyToMany @JsonIgnore
	private List<User> contributers=new ArrayList<>();
	
	
	
	public int getPanelId() {
		return panelId;
	}

	public void setPanelId(int panelId) {
		this.panelId = panelId;
	}

	public List<User> getContributers() {
		return contributers;
	}

	public void setContributers(List<User> contributers) {
		this.contributers = contributers;
	}

	
}
