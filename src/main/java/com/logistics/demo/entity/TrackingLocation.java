package com.logistics.demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TRACKING_LOCATION")
public class TrackingLocation {

	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "PARENT_ID")
	private String parentId;
	@Column(name = "LATITUDE")
	private Long latitude;
	@Column(name = "LONGITUDE")
	private Long longitude;
	@Column(name = "CREATED_ON")
	private Date createdOn;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PARENT_ID", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private LogisticUser logisticUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public LogisticUser getLogisticUser() {
		return logisticUser;
	}

	public void setLogisticUser(LogisticUser logisticUser) {
		this.logisticUser = logisticUser;
	}

}
