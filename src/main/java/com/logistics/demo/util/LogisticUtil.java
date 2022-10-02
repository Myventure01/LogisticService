package com.logistics.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.logistics.demo.entity.LogisticUser;
import com.logistics.demo.entity.TrackingLocation;
import com.logistics.demo.model.Location;
import com.logistics.demo.model.LocationCreationModel;
import com.logistics.demo.model.UpdateUserLocModel;
import com.logistics.demo.model.UserLocationModel;
import com.logistics.demo.model.UserModel;

public class LogisticUtil {
	
	public static String generateRamdonUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static LogisticUser getUserEntity(UserModel model) {
		LogisticUser user=new LogisticUser();
		BeanUtils.copyProperties(model, user);
		if(user.getUserId()==null) {
			user.setUserId(generateRamdonUUID());
		}
		return user;
		
	}
	
	public static TrackingLocation getUserTrackingLocationEntity(UpdateUserLocModel updateUserLocModel) {
		TrackingLocation trackingLocation=new TrackingLocation();
		trackingLocation.setId(generateRamdonUUID());
		trackingLocation.setParentId(updateUserLocModel.getUserId());
		trackingLocation.setCreatedOn(updateUserLocModel.getCreatedOn());
		trackingLocation.setLatitude(updateUserLocModel.getLocation().getLatitude());
		trackingLocation.setLongitude(updateUserLocModel.getLocation().getLongitude());
		return trackingLocation;
	}
	
	public static UserLocationModel getUserLocationModel(List<TrackingLocation> trackingLocationList) {
		UserLocationModel userLocationModel=new UserLocationModel();
		List<LocationCreationModel> locations=new ArrayList<LocationCreationModel>();
		userLocationModel.setUserId(trackingLocationList.get(0).getParentId());
		for(TrackingLocation list:trackingLocationList) {
			LocationCreationModel locationCreationModel=new LocationCreationModel();
			locationCreationModel.setCreatedOn(list.getCreatedOn());
			Location location=new Location();
			location.setLatitude(list.getLatitude());
			location.setLongitude(list.getLongitude());
			locationCreationModel.setLocation(location);
			locations.add(locationCreationModel);
		}		
		userLocationModel.setLocations(locations);
		return userLocationModel;
	}
}
