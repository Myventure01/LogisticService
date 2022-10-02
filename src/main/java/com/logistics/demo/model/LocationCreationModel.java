package com.logistics.demo.model;

import java.util.Date;

public class LocationCreationModel {

	private Date createdOn;
	private Location location;
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}
