package com.logistics.demo.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.demo.entity.LogisticUser;
import com.logistics.demo.entity.TrackingLocation;
import com.logistics.demo.exception.InvalidUserException;
import com.logistics.demo.exception.NoLocationException;
import com.logistics.demo.model.Location;
import com.logistics.demo.model.UpdateUserLocModel;
import com.logistics.demo.model.UserLatestLocationModel;
import com.logistics.demo.model.UserLocationModel;
import com.logistics.demo.model.UserModel;
import com.logistics.demo.repository.TrackingLocationRepository;
import com.logistics.demo.repository.UserRepository;
import com.logistics.demo.service.LogisticService;
import com.logistics.demo.util.LogisticUtil;

@Service
public class LogisticServiceImpl implements LogisticService {

	Logger logger = LogManager.getLogger(LogisticServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TrackingLocationRepository trackingLocationRepository;

	@Override
	public UserModel saveUserData(UserModel userModel) {
		if (userModel != null) {
			LogisticUser userEntity = LogisticUtil.getUserEntity(userModel);
			userEntity = userRepository.save(userEntity);
			BeanUtils.copyProperties(userEntity, userModel);
		}
		return userModel;
	}

	@Override
	public UpdateUserLocModel updateUserLocation(UpdateUserLocModel updateUserLocModel)
			throws InvalidUserException, Exception {
		if (updateUserLocModel != null) {
			if (userRepository.findById(updateUserLocModel.getUserId()).isPresent()) {
				TrackingLocation trackingLocation = LogisticUtil.getUserTrackingLocationEntity(updateUserLocModel);
				trackingLocation = trackingLocationRepository.saveAndFlush(trackingLocation);
				BeanUtils.copyProperties(trackingLocation, updateUserLocModel);
			} else {
				logger.error("Invalid user Exception");
				throw new InvalidUserException("Invalid user!");
			}
		}
		return updateUserLocModel;
	}

	@Override
	public UserLatestLocationModel getUserLatestLocation(String userId) throws InvalidUserException {
		UserLatestLocationModel model = new UserLatestLocationModel();
		Optional<LogisticUser> userEntity = userRepository.findById(userId);

		if (userEntity.isPresent()) {
			LogisticUser latestLoclogisticUser = userEntity.get();
			TrackingLocation location = latestLoclogisticUser.getTrackingLocations().stream()
					.max(Comparator.comparing(TrackingLocation::getCreatedOn)).get();
			latestLoclogisticUser.getTrackingLocations().clear();
			latestLoclogisticUser.getTrackingLocations().add(location);

			BeanUtils.copyProperties(latestLoclogisticUser, model);
			Location loc = new Location();
			loc.setLatitude(location.getLatitude());
			loc.setLongitude(location.getLatitude());
			model.setLocation(loc);
		} else {
			logger.error("Invalid user Exception");
			throw new InvalidUserException("Invalid user!");
		}
		return model;
	}

	@Override
	public UserLocationModel getUserLocationById(String userId) throws NoLocationException, Exception {
		UserLocationModel model=new UserLocationModel();
		model.setUserId(userId);
		List<TrackingLocation> trackingLocationList=trackingLocationRepository.findByParentId(userId);
		if(!trackingLocationList.isEmpty()) {
			model=LogisticUtil.getUserLocationModel(trackingLocationList);
		}
		else {
			logger.error("No Locations Found exception!");
			throw new NoLocationException("No Locations logged!");
		}
		return model;
	}
}
