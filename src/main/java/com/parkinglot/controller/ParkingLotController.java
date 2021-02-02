package com.parkinglot.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkinglot.model.ParkingRequest;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.utilty.GeneralUtility;

@RestController
@RequestMapping({ "/v1" })
public class ParkingLotController {

	@Autowired
	ParkingLotService parkingLotService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ParkingLotController.class);
	private static final String INTERNAL_SERVER_ERROR = "An exception occured while doing operation";

	@PostMapping({ "/parking" })
	public ResponseEntity<Object> bookParking(@RequestBody ParkingRequest parkingRequest) {
		LOGGER.info("Got add a new Parking reuest, request payload: {}", parkingRequest);
		try {
			Vehicle vehicle = GeneralUtility.getVehicleFromRequest(parkingRequest);
			parkingLotService.addNewParking(vehicle);
			LOGGER.info("parking alloted successfully");
			return new ResponseEntity<>("parking alloted successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error(INTERNAL_SERVER_ERROR, e);
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping({ "/parking/{registrationNumber}" })
	public ResponseEntity<Object> getParkingDetails(@PathVariable String registrationNumber) {
		try {
			Vehicle vehicle = parkingLotService.getParkingDetails(registrationNumber);
			return new ResponseEntity<>(vehicle, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(INTERNAL_SERVER_ERROR, e);
			return new ResponseEntity<>(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping({ "/parking" })
	public ResponseEntity<List<ParkingSpot>> getAvailableSpot() {
		LOGGER.info("Got getAvailableSpot reuest");
		try {
			List<ParkingSpot> ParkingSpots = parkingLotService.getAvailableSpot();
			return new ResponseEntity<>(ParkingSpots, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(INTERNAL_SERVER_ERROR, e);
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}