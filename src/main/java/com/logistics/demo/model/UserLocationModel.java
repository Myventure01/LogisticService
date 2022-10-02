package com.logistics.demo.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class UserLocationModel {
	@ApiModelProperty(hidden = true)
	private String message;
	private String userId;
	private List<LocationCreationModel> locations;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<LocationCreationModel> getLocations() {
		return locations;
	}
	public void setLocations(List<LocationCreationModel> locations) {
		this.locations = locations;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserLocationModel(String message, String userId, List<LocationCreationModel> locations) {
		super();
		this.message = message;
		this.userId = userId;
		this.locations = locations;
	}
	
	public UserLocationModel() {
	}
}
