package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Publish;

public interface PublishRepo extends JpaRepository<Publish, Integer> {

}
