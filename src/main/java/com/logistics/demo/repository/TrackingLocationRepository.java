package com.logistics.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logistics.demo.entity.TrackingLocation;
@Repository
public interface TrackingLocationRepository extends JpaRepository<TrackingLocation, String>{
	
	public List<TrackingLocation> findByParentId(String userId);
}
