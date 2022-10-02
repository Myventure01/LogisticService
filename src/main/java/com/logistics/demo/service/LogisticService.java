package com.logistics.demo.service;

import com.logistics.demo.exception.InvalidUserException;
import com.logistics.demo.model.UpdateUserLocModel;
import com.logistics.demo.model.UserLatestLocationModel;
import com.logistics.demo.model.UserLocationModel;
import com.logistics.demo.model.UserModel;

public interface LogisticService {

	public UserModel saveUserData(UserModel user);
	public UpdateUserLocModel updateUserLocation(UpdateUserLocModel updateUserLocModel) throws InvalidUserException, Exception;
	public UserLatestLocationModel getUserLatestLocation(String userId) throws InvalidUserException, Exception;
	public UserLocationModel getUserLocationById(String userId) throws InvalidUserException, Exception;
}
