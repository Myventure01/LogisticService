package com.logistics.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistics.demo.entity.LogisticUser;

@Repository
public interface UserRepository extends JpaRepository<LogisticUser, String> {
		
}
