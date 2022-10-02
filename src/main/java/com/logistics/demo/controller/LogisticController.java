package com.logistics.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.demo.model.UpdateUserLocModel;
import com.logistics.demo.model.UserLatestLocationModel;
import com.logistics.demo.model.UserLocationModel;
import com.logistics.demo.model.UserModel;
import com.logistics.demo.service.LogisticService;

@RestController
@RequestMapping("/logistics")
public class LogisticController {

	@Autowired
	private LogisticService logisticService;
	
	@PostMapping(value ="/saveuser", produces = "application/json", consumes = "application/json")
	private ResponseEntity<UserModel> saveUserDetails(@RequestBody UserModel userModel){
		try {
			UserModel model=logisticService.saveUserData(userModel);
			return new ResponseEntity<UserModel>(model,HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<UserModel>(userModel,HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping(value ="/updateuserlocation", produces = "application/json", consumes = "application/json")
	private ResponseEntity<UpdateUserLocModel> updateLocation(@RequestBody UpdateUserLocModel updateUserLocModel){
		try {
			UpdateUserLocModel model=logisticService.updateUserLocation(updateUserLocModel);
			return new ResponseEntity<UpdateUserLocModel>(model,HttpStatus.CREATED);
		}
		catch (Exception e) {
			updateUserLocModel.setMessage(e.getMessage());
			return new ResponseEntity<UpdateUserLocModel>(updateUserLocModel,HttpStatus.BAD_REQUEST);
		}	
	}

	@GetMapping(value ="/getlatestlocation", produces = "application/json")
	private ResponseEntity<UserLatestLocationModel> getUserLatestLocation(@RequestParam String userId){
		UserLatestLocationModel model=new UserLatestLocationModel();
		try {
			model=logisticService.getUserLatestLocation(userId);
			return new ResponseEntity<UserLatestLocationModel>(model,HttpStatus.CREATED);
		}
		catch (Exception e) {
			model.setMessage(e.getMessage());
			return new ResponseEntity<UserLatestLocationModel>(model,HttpStatus.BAD_REQUEST);
		}	
	}
	
	@GetMapping(value ="/getuserlocations", produces = "application/json")
	private ResponseEntity<UserLocationModel> getUserLocations(@RequestParam String userId){
		UserLocationModel model=new UserLocationModel();
		try {
			model=logisticService.getUserLocationById(userId);
			return new ResponseEntity<UserLocationModel>(model,HttpStatus.CREATED);
		}
		catch (Exception e) {
			model.setMessage(e.getMessage());
			return new ResponseEntity<UserLocationModel>(model,HttpStatus.BAD_REQUEST);
		}	
	}
		
}
