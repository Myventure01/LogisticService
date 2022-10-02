package com.logistics.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LOGISTIC_USER")
public class LogisticUser {

	@Id
	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "SECOND_NAME")
	private String secondName;
	@Column(name = "USER_EMAIL")
	private String email;
	@Column(name = "CREATED_ON")
	private Date createdOn;

	@OneToMany(mappedBy = "logisticUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TrackingLocation> trackingLocations;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public List<TrackingLocation> getTrackingLocations() {
		return trackingLocations;
	}

	public void setTrackingLocations(List<TrackingLocation> trackingLocations) {
		this.trackingLocations = trackingLocations;
	}

}
